package site.rentofficevn.utils;

import org.springframework.stereotype.Component;
import site.rentofficevn.constant.SystemConstant;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {

    public Map<String, String> getMessage(String message) {
        Map<String, String> result = new HashMap<>();
        switch (message) {
            case SystemConstant.UPDATE_SUCCESS:
                result.put(SystemConstant.MESSAGE, "Cập nhật tòa nhà thành công");
                result.put(SystemConstant.ALERT, SystemConstant.UPDATE_SUCCESS);
                break;
            case SystemConstant.INSERT_SUCCESS:
                result.put(SystemConstant.MESSAGE, "Thêm mới tòa nhà thành công");
                result.put(SystemConstant.ALERT, SystemConstant.INSERT_SUCCESS);
                break;
            case SystemConstant.DELETE_SUCCESS:
                result.put(SystemConstant.MESSAGE, "Xóa tòa nhà thành công");
                result.put(SystemConstant.ALERT, SystemConstant.DELETE_SUCCESS);
                break;
            case SystemConstant.ASSIGN_SUCCESS:
                result.put(SystemConstant.MESSAGE, "Giao tòa nhà thành công");
                result.put(SystemConstant.ALERT, SystemConstant.ASSIGN_SUCCESS);
				break;
			case SystemConstant.ERROR_SYSTEM:
                result.put(SystemConstant.MESSAGE, "Error system");
                result.put(SystemConstant.ALERT, SystemConstant.DANGER);
                break;
        }
        return result;
    }
}
