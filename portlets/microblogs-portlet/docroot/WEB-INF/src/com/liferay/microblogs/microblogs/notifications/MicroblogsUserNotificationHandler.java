/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.microblogs.notifications;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.microblogs.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

/**
 * @author Jonathan Lee
 */
public class MicroblogsUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public MicroblogsUserNotificationHandler() {
		setPortletId(PortletKeys.MICROBLOGS);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		AssetRenderer assetRenderer = getAssetRenderer(jsonObject);

		if (assetRenderer == null) {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent.getUserNotificationEventId());

			return null;
		}

		return StringUtil.replace(
			getBodyTemplate(), new String[] {"[$BODY$]", "[$TITLE$]"},
			new String[] {
				HtmlUtil.escape(
					StringUtil.shorten(jsonObject.getString("entryTitle"), 70)),
				getBodyTitle(jsonObject, assetRenderer, serviceContext)
			});
	}

	protected String getBodyTitle(
			JSONObject jsonObject, AssetRenderer assetRenderer,
			ServiceContext serviceContext)
		throws PortalException {

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
				assetRenderer.getClassPK());

		String title = StringPool.BLANK;

		String userFullName = HtmlUtil.escape(
			PortalUtil.getUserName(
				microblogsEntry.getUserId(), StringPool.BLANK));

		long parentMicroblogsEntryId =
			MicroblogsUtil.getParentMicroblogsEntryId(microblogsEntry);

		if (MicroblogsUtil.isTaggedUser(
				microblogsEntry.getMicroblogsEntryId(), false,
				serviceContext.getUserId())) {

			title = serviceContext.translate(
				"x-tagged-you-in-a-post", userFullName);
		}
		else if (microblogsEntry.getType() ==
					MicroblogsEntryConstants.TYPE_REPLY) {

			if (MicroblogsUtil.getParentMicroblogsUserId(microblogsEntry) ==
					serviceContext.getUserId()) {

				title = serviceContext.translate(
					"x-commented-on-your-post", userFullName);
			}
			else if (MicroblogsUtil.hasReplied(
						parentMicroblogsEntryId,
						serviceContext.getUserId())) {

				User receiverUser = UserLocalServiceUtil.fetchUser(
					microblogsEntry.getReceiverUserId());

				if (receiverUser != null) {
					title = serviceContext.translate(
						"x-also-commented-on-x's-post", userFullName,
						receiverUser.getFullName());
				}
			}
			else if (MicroblogsUtil.isTaggedUser(
						parentMicroblogsEntryId, true,
						serviceContext.getUserId())) {

				title = serviceContext.translate(
					"x-commented-on-a-post-you-are-tagged-in", userFullName);
			}
		}

		return title;
	}

}