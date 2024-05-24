<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerEditURL" value="/admin/customer-edit"/>
<html>
<head>
    <c:if test="${empty customerId}">
        <title>Thêm khách hàng</title>
    </c:if>
    <c:if test="${not empty customerId}">
        <title>Chỉnh sửa thông tin khách hàng</title>
    </c:if>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <c:if test="${empty customerId}">
                    <li class="active">Thêm khách hàng</li>
                </c:if>
                <c:if test="${not empty customerId}">
                    <li class="active">Chỉnh sửa thông tin khách hàng</li>
                </c:if>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
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
                    <form:form modelAttribute="customer" id="formEdit" cssClass="form-horizontal">
                        <div class="form-group">
                            <div class="col-xs-9">
                                <form:hidden path="id" id="customerId" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="fullName">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="fullName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="phone">Di động</label>
                            <div class="col-xs-9">
                                <form:input path="phone" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="email">Email</label>
                            <div class="col-xs-9">
                                <form:input path="email" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="requirement">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input path="requirement" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="note">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:textarea path="note" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="status">Tình trạng</label>
                            <div class="col-xs-9">
                                <form:input path="status" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>
                            <div class="col-xs-9">
                                <c:if test="${not empty customer.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Cập nhật"/>
                                </c:if>
                                <c:if test="${empty customer.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Thêm"/>
                                </c:if>
                                <input id="btnCancel" type="button" class="btn btn-warning" value="Huỷ"/>
                                <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                            </div>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.row -->

            <br>
            <div class="row">
                <div class="col-xs-12">
                    <h4 style="display: inline-block; color: #3a87ad">
                        QUÁ TRÌNH CSKH
                        <button type="button"
                                class="btn btn-white btn-info btn-bold"
                                data-toggle="tooltip"
                                onclick="createTraction(CHAM_SOC_KHACH_HANG)"
                                title="Thêm giao dịch">
                            <span><em class="fa fa-plus-circle bigger-110 purple"></em></span>
                            <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image_trans">
                        </button>
                    </h4>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th style="width: 15%;">Ngày tạo</th>
                            <th style="width: 20%;">Nguời tạo</th>
                            <th style="width: 65%;">Ghi chú</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>
                                <form id="CHAM_SOC_KHACH_HANG">
                                    <input type="hidden" name="code" value="CHAM_SOC_KHACH_HANG"/>
                                    <input type="hidden" name="customerId" value="1"/>
                                    <input class="form-control" name="note"/>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <br>
            <div class="row">
                <div class="col-xs-12">
                    <h4 style="display: inline-block; color: #3a87ad">
                        DẪN ĐI XEM
                        <button type="button"
                                class="btn btn-white btn-info btn-bold"
                                data-toggle="tooltip"
                                onclick="createTraction(DAN_DI_XEM)"
                                title="Thêm giao dịch">
                            <span><em class="fa fa-plus-circle bigger-110 purple"></em></span>
                            <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image_trans">
                        </button>
                    </h4>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th style="width: 15%;">Ngày tạo</th>
                            <th style="width: 20%;">Nguời tạo</th>
                            <th style="width: 65%;">Ghi chú</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>
                                <form id="DAN_DI_XEM">
                                    <input type="hidden" name="code" value="DAN_DI_XEM"/>
                                    <input type="hidden" name="customerId" value="1"/>
                                    <input class="form-control" name="note"/>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div> <!-- PAGE CONTENT ENDS -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<script>
    $("#btnSave").click(function (e) {
        e.preventDefault();
        let title;
        let message;
        let data = {};
        const formData = $("#formEdit").serializeArray();
        $.each(formData, function (index, v) {
            data["" + v.name + ""] = v.value;
        });
        $('#loading_image').show();
        let id = $('#customerId').val();
        if ('' === id) {
            title = 'Thêm';
            message = 'Thêm tòa nhà mới?';
        } else {
            title = 'Cập nhật';
            message = 'Cập nhật tòa nhà này?';
        }
        showMessageConfirmation(function () {
            if ('' === id) {
                addCustomer(data);
            } else {
                updateCustomer(data, id);
            }
        }, title, message)
    });

    function addCustomer(data) {
        $.ajax({
            url: '${customerAPI}',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#loading_image').hide();
                window.location.href = "${customerEditURL}-" + response.id + "?message=insert_success";
                window.location.href = "/admin/customer-list";
            },
            error: function () {
                $('#loading_image').hide();
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    function updateCustomer(data) {
        $.ajax({
            url: '${customerAPI}',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#loading_image').hide();
                window.location.href = "${customerEditURL}-" + response.id + "?message=update_success";
                window.location.href = "/admin/customer-list";
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $("#btnCancel").click(function () {
        let id = $('#customerId').val();

        if ('' !== id) {
            showAlertBeforeCanceling("/admin/customer-list");
        }
    });
</script>
</body>
</html>
