package com.driverstack.devicemanager.remoteservice;

import java.util.List;
import java.util.Map;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

import com.driverstack.yunos.remote.vo.AccessToken;
import com.driverstack.yunos.remote.vo.Device;
import com.driverstack.yunos.remote.vo.User;

public interface RemoteService {

	@POST("/users")
	boolean createUser(@Body User user);

	@POST("/auth/requestAccessToken")
	AccessToken requestAccessToken();

	@GET("/auth/destroyToken")
	boolean destroyToken();

	@POST("/auth/changePassword")
	boolean changePassword(@Query("newPassword") String newPassword);

	@GET("/users/{userId}")
	User getUser(@Path("userId") String userId);

	@POST("/devices")
	String addDevice(@Query("userId") String userId, @Body Device device);

	@GET("/devices")
	List<com.driverstack.yunos.remote.vo.Device> getUserDevices(
			@Query("userId") String userId);

	@POST("/devices/update")
	boolean updateDevice(@Body Device device);

	@DELETE("/devices/{deviceId}")
	boolean deleteDevice(@Path("deviceId") String deviceId);

	@GET("/devices/{deviceId}/configuration")
	List<com.driverstack.yunos.remote.vo.ConfigurationItem> getDeviceConfiguration(
			@Path("deviceId") String deviceId);

	@GET("/devices/{deviceId}/initialConfiguration")
	List<com.driverstack.yunos.remote.vo.ConfigurationItem> getDeviceInitialConfiguration(
			@Path("deviceId") String deviceId,
			@Query("driverId") String driverId);

	@POST("/devices/{deviceId}/configuration")
	boolean updateDeviceConfiguration(@Path("deviceId") String deviceId,
			@Body List<com.driverstack.yunos.remote.vo.ConfigurationItem> items);

	@GET("/devices/reload")
	boolean reloadDriver(@Query("deviceId") String deviceId);

	@GET("/functionalDevices/by-device")
	List<com.driverstack.yunos.remote.vo.FunctionalDevice> getFunctionalDevices(
			@Query("deviceId") String deviceId, @Query("locale") String locale);

	@GET("/functionalDevices/by-user")
	List<com.driverstack.yunos.remote.vo.FunctionalDevice> getFunctionalDevices(
			@Query("userId") String userId,
			@Query("className") String className, @Query("locale") String locale);

	@GET("/vendors")
	List<com.driverstack.yunos.remote.vo.Vendor> getAllVendors(
			@Query("locale") String locale);

	@GET("/deviceClasses")
	List<com.driverstack.yunos.remote.vo.DeviceClass> getDeviceClasses(
			@Query("vendorId") String vendorId, @Query("locale") String locale);

	@GET("/vendors/{vendorId}/models")
	List<com.driverstack.yunos.remote.vo.Model> getModels(
			@Path("vendorId") String vendorId,
			@Query("deviceClassId") String deviceClassId,
			@Query("locale") String locale);

	@GET("/drivers")
	List<com.driverstack.yunos.remote.vo.Driver> getDrivers(
			@Query("modelId") String deviceClassId);

	@GET("/drivers/{driverId}/configrationItems")
	List<com.driverstack.yunos.remote.vo.DriverConfigurationDefinitionItem> getDriverConfigurationDefinitionItems(
			@Path("driverId") String driverId, @Query("locale") String locale);

	@POST("/devices/{deviceId}/{functionalDeviceIndex}/{operation}")
	boolean operateDevice(@Query("appId") String appId,
			@Path("deviceId") String deviceId,
			@Path("functionalDeviceIndex") int functionalDeviceIndex,
			@Path("operation") String operation,
			@QueryMap Map<String, String> paramMap);

}
