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

package com.liferay.sampleservicebuilder.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sampleservicebuilder.model.Bar;

/**
 * The persistence interface for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistenceImpl
 * @see BarUtil
 * @generated
 */
public interface BarPersistence extends BasePersistence<Bar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public void cacheResult(com.liferay.sampleservicebuilder.model.Bar bar);

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public void cacheResult(
		java.util.List<com.liferay.sampleservicebuilder.model.Bar> bars);

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public com.liferay.sampleservicebuilder.model.Bar create(long barId);

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sampleservicebuilder.model.Bar remove(long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchBarException;

	public com.liferay.sampleservicebuilder.model.Bar updateImpl(
		com.liferay.sampleservicebuilder.model.Bar bar)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the bar with the primary key or throws a {@link com.liferay.sampleservicebuilder.NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws com.liferay.sampleservicebuilder.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sampleservicebuilder.model.Bar findByPrimaryKey(
		long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchBarException;

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sampleservicebuilder.model.Bar fetchByPrimaryKey(
		long barId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the bars.
	*
	* @return the bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sampleservicebuilder.model.Bar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sampleservicebuilder.model.Bar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sampleservicebuilder.model.Bar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the bars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the foos associated with the bar.
	*
	* @param pk the primary key of the bar
	* @return the foos associated with the bar
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of foos associated with the bar.
	*
	* @param pk the primary key of the bar
	* @return the number of foos associated with the bar
	* @throws SystemException if a system exception occurred
	*/
	public int getFoosSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the foo is associated with the bar.
	*
	* @param pk the primary key of the bar
	* @param fooPK the primary key of the foo
	* @return <code>true</code> if the foo is associated with the bar; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsFoo(long pk, long fooPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the bar has any foos associated with it.
	*
	* @param pk the primary key of the bar to check for associations with foos
	* @return <code>true</code> if the bar has any foos associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsFoos(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param fooPK the primary key of the foo
	* @throws SystemException if a system exception occurred
	*/
	public void addFoo(long pk, long fooPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param foo the foo
	* @throws SystemException if a system exception occurred
	*/
	public void addFoo(long pk, com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param fooPKs the primary keys of the foos
	* @throws SystemException if a system exception occurred
	*/
	public void addFoos(long pk, long[] fooPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param foos the foos
	* @throws SystemException if a system exception occurred
	*/
	public void addFoos(long pk,
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the bar and its foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar to clear the associated foos from
	* @throws SystemException if a system exception occurred
	*/
	public void clearFoos(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param fooPK the primary key of the foo
	* @throws SystemException if a system exception occurred
	*/
	public void removeFoo(long pk, long fooPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the bar and the foo. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param foo the foo
	* @throws SystemException if a system exception occurred
	*/
	public void removeFoo(long pk,
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param fooPKs the primary keys of the foos
	* @throws SystemException if a system exception occurred
	*/
	public void removeFoos(long pk, long[] fooPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the bar and the foos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param foos the foos
	* @throws SystemException if a system exception occurred
	*/
	public void removeFoos(long pk,
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the foos associated with the bar, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param fooPKs the primary keys of the foos to be associated with the bar
	* @throws SystemException if a system exception occurred
	*/
	public void setFoos(long pk, long[] fooPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the foos associated with the bar, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the bar
	* @param foos the foos to be associated with the bar
	* @throws SystemException if a system exception occurred
	*/
	public void setFoos(long pk,
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos)
		throws com.liferay.portal.kernel.exception.SystemException;
}