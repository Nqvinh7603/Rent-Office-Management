<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/user/list"/>
<c:url var="formAjax" value="/api/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <%--<spring:message code="label.user.list"/>--%>
        Danh sách người dùng
    </title>
</head>

<body>
<div class="main-content">
    <form:form modelAttribute="model" action="${formUrl}" id="listForm" method="GET">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">
                                <%--<spring:message code="label.home"/>--%>
                            Trang chủ
                        </a>
                    </li>
                    <li class="active">
                            <%--<spring:message code="label.user.list"/>--%>
                        Danh sách người dùng
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title">
                                                <%--<spring:message code="label.search"/>--%>
                                            Tìm kiếm
                                        </h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">
                                                            <%--<spring:message code="label.search.value"/>--%>
                                                        Tìm kiếm tên người dùng
                                                    </label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="searchValue"
                                                                        cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button id="btnSearch" type="button"
                                                                class="btn btn-sm btn-success">
                                                                <%--spring:message code="label.search"/>--%>
                                                            Tìm kiếm
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <a flag="info"
                                           class="btn btn-corner btn-bold"
                                           data-toggle="tooltip"
                                            <%--title='<spring:message code="label.user.add"/>'--%>
                                           title="Thêm người dùng"
                                           href='<c:url value="/admin/user/edit"/>'>
                                            <span><em class="fa fa-plus-circle"></em></span>
                                        </a>
                                        <button id="btnDelete" type="button" disabled
                                                class="btn btn-danger btn-bold"
                                                data-toggle="tooltip"
                                                title="Xóa người dùng" onclick="warningBeforeDelete()">
                                            <span><em class="fa fa-trash"></em></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table id="tableList"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                        <thead>
                                        <tr>
                                            <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                                <th class="left select-cell">
                                                    <fieldset class="form-group"><input type="checkbox" id="checkAll"
                                                                                        class="check-box-element">
                                                    </fieldset>
                                                </th>
                                            </security:authorize>
                                            <th class="text-left">Tên đăng nhập</th>
                                            <th class="text-left">Họ và tên đầy đủ</th>
                                            <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                                <th class="col-actions">Thao tác</th>
                                            </security:authorize>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                                    <td>
                                                        <input type="checkbox" name="customerIds"
                                                               value="${item.id}"
                                                               id="checkbox_${item.id}" class="check-box-element"/>
                                                    </td>
                                                </security:authorize>
                                                <td>${item.userName}</td>
                                                <td>${item.fullName}</td>
                                                <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                                    <td>
                                                        <c:if test="${item.roleCode != 'ADMIN'}">
                                                            <c:url var="editUser" value="/admin/user/edit/${item.id}">
                                                                <c:param name="id" value="${item.id}"/>
                                                            </c:url>
                                                            <a class="btn btn-xs btn-info" data-toggle="tooltip"
                                                               title="Cập nhật thông tin người dùng" href='${editUser}'><i
                                                                    class="fa fa-pencil-square-o"
                                                                    aria-hidden="true"></i>
                                                            </a>
                                                        </c:if>
                                                        <c:if test="${item.roleCode == 'ADMIN'}">
                                                            <p>Không được thao tác</p>
                                                        </c:if>
                                                    </td>
                                                </security:authorize>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <ul id="pagination" class="pagination"></ul>
                        </div>
                        <form:hidden path="page" id="page"/>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#page').val(1);
            $('#listForm').submit();
        });
    });

    var totalPages = ${model.totalPages};
    var currentPage = ${model.page};
    var totalItems = ${model.totalItems};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage !== page) {
                    $('#page').val(page);
                    $('#listForm').submit();
                }
            },
            // Text labels
            first: 'Trang đầu',
            prev: 'Trang trước',
            next: 'Tiếp theo',
            last: 'Trang cuối',
        });
    });

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteUser(dataArray);
        });
    }

    function deleteUser(data) {
        $.ajax({
            url: '${formAjax}/',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/user/list?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/user/list?message=error_system'/>";
            }
        });
    }
</script>
</body>

</html>