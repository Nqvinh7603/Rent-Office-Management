package site.rentofficevn.controller.admin;

import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.dto.UserDTO;
import site.rentofficevn.security.utils.SecurityUtils;
import site.rentofficevn.service.IUserService;
import site.rentofficevn.service.impl.RoleService;
import site.rentofficevn.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@Controller(value = "usersControllerOfAdmin")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageUtils messageUtil;

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
    public ModelAndView getUsers(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        List<UserDTO> users = userService.getUsers(model.getSearchValue(),
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(users);
        model.setTotalItems(userService.getTotalItems(model.getSearchValue()));
        model.setTotalPage((int) Math.ceil((double) model.getTotalItems() / model.getMaxPageItems()));
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        model.setRoleDTOs(roleService.getRoles());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/profile/{username}", method = RequestMethod.GET)
    public ModelAndView updateProfile(@PathVariable("username") String username, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/profile");
        UserDTO model = userService.findOneByUserName(username);
        initMessageResponse(mav, request);
        model.setRoleDTOs(roleService.getRoles());
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView updateUser(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        UserDTO model = userService.findUserById(id);
        model.setRoleDTOs(roleService.getRoles());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/profile/password", method = RequestMethod.GET)
    public ModelAndView updatePassword(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/password");
        UserDTO model = userService.findOneByUserName(SecurityUtils.getPrincipal().getUsername());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessageForUser(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
