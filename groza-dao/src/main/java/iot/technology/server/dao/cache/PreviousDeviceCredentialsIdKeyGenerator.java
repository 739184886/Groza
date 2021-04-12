package iot.technology.server.dao.cache;

import iot.technology.server.common.data.security.DeviceCredentials;
import iot.technology.server.dao.device.DeviceCredentialsService;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class PreviousDeviceCredentialsIdKeyGenerator implements KeyGenerator {

    public static final String NOT_VALID_DEVICE = "notValidDeviceCredentialsId";

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        DeviceCredentialsService deviceCredentialsService = (DeviceCredentialsService) o;
        DeviceCredentials deviceCredentials = (DeviceCredentials) objects[0];
        if (deviceCredentials.getDeviceId() != null) {
            DeviceCredentials oldDeviceCredentials = deviceCredentialsService.findDeviceCredentialsByDeviceId(deviceCredentials.getDeviceId());
            if (oldDeviceCredentials != null) {
                return oldDeviceCredentials.getCredentialsId();
            }
        }
        return NOT_VALID_DEVICE;
    }
}
