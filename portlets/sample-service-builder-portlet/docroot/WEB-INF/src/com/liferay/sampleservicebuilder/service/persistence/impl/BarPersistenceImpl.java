/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sampleservicebuilder.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.portal.service.persistence.impl.TableMapperFactory;

import com.liferay.sampleservicebuilder.NoSuchBarException;
import com.liferay.sampleservicebuilder.model.Bar;
import com.liferay.sampleservicebuilder.model.impl.BarImpl;
import com.liferay.sampleservicebuilder.model.impl.BarModelImpl;
import com.liferay.sampleservicebuilder.service.persistence.BarPersistence;
import com.liferay.sampleservicebuilder.service.persistence.FooPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistence
 * @see BarUtil
 * @generated
 */
public class BarPersistenceImpl extends BasePersistenceImpl<Bar>
	implements BarPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BarImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public BarPersistenceImpl() {
		setModelClass(Bar.class);
	}

	/**
	 * Caches the bar in the entity cache if it is enabled.
	 *
	 * @param bar the bar
	 */
	@Override
	public void cacheResult(Bar bar) {
		EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey(), bar);

		bar.resetOriginalValues();
	}

	/**
	 * Caches the bars in the entity cache if it is enabled.
	 *
	 * @param bars the bars
	 */
	@Override
	public void cacheResult(List<Bar> bars) {
		for (Bar bar : bars) {
			if (EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
						BarImpl.class, bar.getPrimaryKey()) == null) {
				cacheResult(bar);
			}
			else {
				bar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all bars.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BarImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BarImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the bar.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Bar bar) {
		EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Bar> bars) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Bar bar : bars) {
			EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
				BarImpl.class, bar.getPrimaryKey());
		}
	}

	/**
	 * Creates a new bar with the primary key. Does not add the bar to the database.
	 *
	 * @param barId the primary key for the new bar
	 * @return the new bar
	 */
	@Override
	public Bar create(long barId) {
		Bar bar = new BarImpl();

		bar.setNew(true);
		bar.setPrimaryKey(barId);

		return bar;
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar that was removed
	 * @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar remove(long barId) throws NoSuchBarException, SystemException {
		return remove((Serializable)barId);
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar that was removed
	 * @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar remove(Serializable primaryKey)
		throws NoSuchBarException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Bar bar = (Bar)session.get(BarImpl.class, primaryKey);

			if (bar == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bar);
		}
		catch (NoSuchBarException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Bar removeImpl(Bar bar) throws SystemException {
		bar = toUnwrappedModel(bar);

		barToFooTableMapper.deleteLeftPrimaryKeyTableMappings(bar.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bar)) {
				bar = (Bar)session.get(BarImpl.class, bar.getPrimaryKeyObj());
			}

			if (bar != null) {
				session.delete(bar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bar != null) {
			clearCache(bar);
		}

		return bar;
	}

	@Override
	public Bar updateImpl(com.liferay.sampleservicebuilder.model.Bar bar)
		throws SystemException {
		bar = toUnwrappedModel(bar);

		boolean isNew = bar.isNew();

		Session session = null;

		try {
			session = openSession();

			if (bar.isNew()) {
				session.save(bar);

				bar.setNew(false);
			}
			else {
				session.merge(bar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
			BarImpl.class, bar.getPrimaryKey(), bar, false);

		bar.resetOriginalValues();

		return bar;
	}

	protected Bar toUnwrappedModel(Bar bar) {
		if (bar instanceof BarImpl) {
			return bar;
		}

		BarImpl barImpl = new BarImpl();

		barImpl.setNew(bar.isNew());
		barImpl.setPrimaryKey(bar.getPrimaryKey());

		barImpl.setBarId(bar.getBarId());

		return barImpl;
	}

	/**
	 * Returns the bar with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar
	 * @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBarException, SystemException {
		Bar bar = fetchByPrimaryKey(primaryKey);

		if (bar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or throws a {@link com.liferay.sampleservicebuilder.NoSuchBarException} if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar
	 * @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar findByPrimaryKey(long barId)
		throws NoSuchBarException, SystemException {
		return findByPrimaryKey((Serializable)barId);
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Bar bar = (Bar)EntityCacheUtil.getResult(BarModelImpl.ENTITY_CACHE_ENABLED,
				BarImpl.class, primaryKey);

		if (bar == _nullBar) {
			return null;
		}

		if (bar == null) {
			Session session = null;

			try {
				session = openSession();

				bar = (Bar)session.get(BarImpl.class, primaryKey);

				if (bar != null) {
					cacheResult(bar);
				}
				else {
					EntityCacheUtil.putResult(BarModelImpl.ENTITY_CACHE_ENABLED,
						BarImpl.class, primaryKey, _nullBar);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BarModelImpl.ENTITY_CACHE_ENABLED,
					BarImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Bar fetchByPrimaryKey(long barId) throws SystemException {
		return fetchByPrimaryKey((Serializable)barId);
	}

	/**
	 * Returns all the bars.
	 *
	 * @return the bars
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Bar> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of bars
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Bar> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bars
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Bar> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Bar> list = (List<Bar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BAR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BAR;

				if (pagination) {
					sql = sql.concat(BarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the bars from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Bar bar : findAll()) {
			remove(bar);
		}
	}

	/**
	 * Returns the number of bars.
	 *
	 * @return the number of bars
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BAR);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns all the foos associated with the bar.
	 *
	 * @param pk the primary key of the bar
	 * @return the foos associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.sampleservicebuilder.model.Foo> getFoos(long pk)
		throws SystemException {
		return getFoos(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the foos associated with the bar.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the bar
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of foos associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.sampleservicebuilder.model.Foo> getFoos(long pk,
		int start, int end) throws SystemException {
		return getFoos(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos associated with the bar.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sampleservicebuilder.model.impl.BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the bar
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of foos associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<com.liferay.sampleservicebuilder.model.Foo> getFoos(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return barToFooTableMapper.getRightBaseModels(pk, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of foos associated with the bar.
	 *
	 * @param pk the primary key of the bar
	 * @return the number of foos associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getFoosSize(long pk) throws SystemException {
		long[] pks = barToFooTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the foo is associated with the bar.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPK the primary key of the foo
	 * @return <code>true</code> if the foo is associated with the bar; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsFoo(long pk, long fooPK) throws SystemException {
		return barToFooTableMapper.containsTableMapping(pk, fooPK);
	}

	/**
	 * Returns <code>true</code> if the bar has any foos associated with it.
	 *
	 * @param pk the primary key of the bar to check for associations with foos
	 * @return <code>true</code> if the bar has any foos associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean containsFoos(long pk) throws SystemException {
		if (getFoosSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPK the primary key of the foo
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addFoo(long pk, long fooPK) throws SystemException {
		barToFooTableMapper.addTableMapping(pk, fooPK);
	}

	/**
	 * Adds an association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param foo the foo
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addFoo(long pk, com.liferay.sampleservicebuilder.model.Foo foo)
		throws SystemException {
		barToFooTableMapper.addTableMapping(pk, foo.getPrimaryKey());
	}

	/**
	 * Adds an association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPKs the primary keys of the foos
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addFoos(long pk, long[] fooPKs) throws SystemException {
		for (long fooPK : fooPKs) {
			barToFooTableMapper.addTableMapping(pk, fooPK);
		}
	}

	/**
	 * Adds an association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param foos the foos
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addFoos(long pk,
		List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws SystemException {
		for (com.liferay.sampleservicebuilder.model.Foo foo : foos) {
			barToFooTableMapper.addTableMapping(pk, foo.getPrimaryKey());
		}
	}

	/**
	 * Clears all associations between the bar and its foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar to clear the associated foos from
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearFoos(long pk) throws SystemException {
		barToFooTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPK the primary key of the foo
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeFoo(long pk, long fooPK) throws SystemException {
		barToFooTableMapper.deleteTableMapping(pk, fooPK);
	}

	/**
	 * Removes the association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param foo the foo
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeFoo(long pk,
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws SystemException {
		barToFooTableMapper.deleteTableMapping(pk, foo.getPrimaryKey());
	}

	/**
	 * Removes the association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPKs the primary keys of the foos
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeFoos(long pk, long[] fooPKs) throws SystemException {
		for (long fooPK : fooPKs) {
			barToFooTableMapper.deleteTableMapping(pk, fooPK);
		}
	}

	/**
	 * Removes the association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param foos the foos
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeFoos(long pk,
		List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws SystemException {
		for (com.liferay.sampleservicebuilder.model.Foo foo : foos) {
			barToFooTableMapper.deleteTableMapping(pk, foo.getPrimaryKey());
		}
	}

	/**
	 * Sets the foos associated with the bar, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param fooPKs the primary keys of the foos to be associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setFoos(long pk, long[] fooPKs) throws SystemException {
		Set<Long> newFooPKsSet = SetUtil.fromArray(fooPKs);
		Set<Long> oldFooPKsSet = SetUtil.fromArray(barToFooTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeFooPKsSet = new HashSet<Long>(oldFooPKsSet);

		removeFooPKsSet.removeAll(newFooPKsSet);

		for (long removeFooPK : removeFooPKsSet) {
			barToFooTableMapper.deleteTableMapping(pk, removeFooPK);
		}

		newFooPKsSet.removeAll(oldFooPKsSet);

		for (long newFooPK : newFooPKsSet) {
			barToFooTableMapper.addTableMapping(pk, newFooPK);
		}
	}

	/**
	 * Sets the foos associated with the bar, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the bar
	 * @param foos the foos to be associated with the bar
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setFoos(long pk,
		List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws SystemException {
		try {
			long[] fooPKs = new long[foos.size()];

			for (int i = 0; i < foos.size(); i++) {
				com.liferay.sampleservicebuilder.model.Foo foo = foos.get(i);

				fooPKs[i] = foo.getPrimaryKey();
			}

			setFoos(pk, fooPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	/**
	 * Initializes the bar persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sampleservicebuilder.model.Bar")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Bar>> listenersList = new ArrayList<ModelListener<Bar>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Bar>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		barToFooTableMapper = TableMapperFactory.getTableMapper("SSB_Foos_Bars",
				"barId", "fooId", this, fooPersistence);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(BarImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = FooPersistence.class)
	protected FooPersistence fooPersistence;
	protected TableMapper<Bar, com.liferay.sampleservicebuilder.model.Foo> barToFooTableMapper;
	private static final String _SQL_SELECT_BAR = "SELECT bar FROM Bar bar";
	private static final String _SQL_COUNT_BAR = "SELECT COUNT(bar) FROM Bar bar";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Bar exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BarPersistenceImpl.class);
	private static Bar _nullBar = new BarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Bar> toCacheModel() {
				return _nullBarCacheModel;
			}
		};

	private static CacheModel<Bar> _nullBarCacheModel = new CacheModel<Bar>() {
			@Override
			public Bar toEntityModel() {
				return _nullBar;
			}
		};
}