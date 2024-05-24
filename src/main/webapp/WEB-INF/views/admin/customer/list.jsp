<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="assignmentAPI" value="/api/customer/assignment-customer"/>
<html>
<head>
    <title>Quản lý khách hàng</title>
</head>
<body>
<div class="main-content">
    <form:form modelAttribute="modelSearch" action="${customerListURL}" id="listForm" method="GET">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Trang chủ</a>
                </li>
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="padding-bottom: 0px">
            <div class="row">
                <c:if test="${null != messageResponse}">
                    <div class="alert alert-block alert-${alert}">
                        <button type="button" class="close" data-dismiss="alert">
                            <em class="ace-icon fa fa-times"></em>
                        </button>
                            ${messageResponse}
                    </div>
                </c:if>
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>
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
                                        <div class="col-xs-4">
                                            <label for="fullName">Tên khách hàng</label>
                                            <form:input path="fullName" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="phone">Di động</label>
                                            <form:input path="phone" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="email">Email</label>
                                            <form:input path="email" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                            <div class="col-xs-4">
                                                <label for="staffId">Nhân viên</label>
                                                <br>
                                                <form:select path="staffId">
                                                    <form:option value="">--- Chọn nhân viên phụ trách ---</form:option>
                                                    <form:options items="${staffmaps}"/>
                                                </form:select>
                                            </div>
                                        </security:authorize>
                                    </div>
                                </div>
                                <button class="btn btn-md btn-success" id="btnSearch" style="margin-top: 10px">Tìm kiếm
                                    <i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </br>
        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right" style="margin-right: 20px">
                        <a class="btn btn-corner btn-bold"
                           data-toggle="tooltip"
                           title="Thêm khách hàng"
                           href='<c:url value="/admin/customer-edit"/>'>
                            <span><em class="fa fa-plus-circle"></em></span>
                        </a>
                        <button id="btnDeleteCustomer" type="button" disabled class="btn btn-danger btn-bold"
                                data-toggle="tooltip" title="Xóa khách hàng">
                            <span><em class="fa fa-trash"></em></span>
                        </button>
                    </div>
                </div>
            </div>
        </security:authorize>
        <br/>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">

                    <display:table name="modelSearch.listResult"
                                   cellspacing="0"
                                   cellpadding="0"
                                   requestURI="${customerListURL}"
                                   partialList="true" sort="external"
                                   size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                   id="customerList" pagesize="${modelSearch.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                        headerClass="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="customerIds" value="${customerList.id}"
                                       id="checkbox_${customerList.id}" class="check-box-element"/>
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                        <display:column headerClass="text-left" property="staffName" title="Nhân viên quản lý"/>
                        <display:column headerClass="text-left" property="phone" title="Di động"/>
                        <display:column headerClass="text-left" property="email" title="Email"/>
                        <display:column headerClass="text-left" property="requirement" title="Nhu cầu"/>
                        <display:column headerClass="text-left" property="createdBy" title="Người nhập"/>
                        <display:column headerClass="text-left" property="createdDate" title="Ngày nhập"/>
                        <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                            <display:column headerClass="col-actions" title="Thao tác">
                                <a class="btn btn-xs btn-info"
                                   data-toggle="tooltip"
                                   title="Sửa thông tin khách hàng"
                                   href='<c:url value='/admin/customer-edit-${customerList.id}'/>'>
                                    <span><em class="ace-icon fa fa-pencil "></em></span>
                                </a>
                                <button class="btn btn-xs" data-toggle="tooltip"
                                        title="Giao khách hàng cho nhân viên quản lý" id="btnCustomerAssignment" customerId="${customerList.id}">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </button>
                            </display:column>
                        </security:authorize>
                    </display:table>
                </div>
            </div>
        </div>
    </div> <!-- /.page-content -->
</div>
</form:form>
</div><!-- /.main-content -->

<!-- assignmentCustomer -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" id="btnCloseModal" data-dismiss="modal">Đóng lại</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        const checkboxAll = $('#checkAll');
        const customerItemsCheckbox = $('#customerList input[type=checkbox][name="customerIds"]');

        customerItemsCheckbox.change(function () {
            const numberOfChecked = $('#customerList input[type=checkbox][name="customerIds"]:checked').length;
            const isCheckedAll = customerItemsCheckbox.length === numberOfChecked;
            checkboxAll.prop('checked', isCheckedAll);

            if (1 <= numberOfChecked) {
                $('#btnDeleteCustomer').attr('disabled', false);
            } else {
                $('#btnDeleteCustomer').attr('disabled', true);
            }
        });

        $('#btnCloseModal').click(function () {
            location.reload();
        });
    });

    $(document).on("click", "#customerList button#btnCustomerAssignment", function (e) {
        e.preventDefault();
        openModalAssignmentCustomer();
        let customerId = $(this).attr('customerId');
        $('#customerId').val(customerId);
        loadStaff(customerId);
    })

    function openModalAssignmentCustomer() {
        $('#assignmentCustomerModal').modal();
    }

    function loadStaff(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + "/staffs",
            dataType: "json",
            success: function (response) {
                console.log('success');
                let row = '';
                $.each(response, function (index, item) {
                    row += '<tr>';
                    row += '<td class = "text-center"><input type="checkbox" value=' + item.id + ' id ="checkbox_' + item.id + '" class = "check-box-element" ' + item.checked + '/></td>';
                    row += '<td class = "text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnAssignCustomer').click(function (e) {
        e.preventDefault();
        let dataLength;
        let data = {};
        data['customerId'] = $('#customerId').val();
        data['staffIds'] = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        dataLength = data['staffIds'].length;
        showConfirmationAlertBeforeAction(function () {
            assignStaff(data);
        }, "Giao", "Giao ".concat(dataLength > 1 ? "các " : "", "nhân viên đã chọn quản lý khách hàng này?"));
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/assignment-customer",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                showNotification('success', 'Giao khách hàng cho nhân viên quản lý thành công!');
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        let customerIds = $('#customerList').find(
            'tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        showConfirmationAlertBeforeAction(function () {
            deleteCustomer(customerIds);
        }, "Xóa", "Xóa ".concat(customerIds > 1 ? "các " : "", "tòa nhà đã chọn?"));
    });

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                window.location.href = "<c:url value ='/admin/customer-list?message=delete_success'/>"
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>
</body>
</html>