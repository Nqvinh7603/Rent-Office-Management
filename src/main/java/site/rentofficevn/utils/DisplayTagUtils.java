package site.rentofficevn.utils;

import org.slf4j.Logger;
import site.rentofficevn.dto.AbstractDTO;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import javax.servlet.http.HttpServletRequest;

public class DisplayTagUtils {

    //private static final Logger log =  Logger.(DisplayTagUtils.class);

    public static void of(HttpServletRequest request, AbstractDTO dto) {
        if (dto != null) {
            String sPage = request.getParameter(new ParamEncoder(dto.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
            Integer page = 1;
            if (StringUtils.isNotBlank(sPage)) {
                try {
                    page = Integer.valueOf(sPage);
                } catch (Exception e) {
                    //log.error(e.getMessage());
                }
            }
            dto.setPage(page);
        }
    }
}