<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>

<html>
<head>
    <title>Danh sách tòa nhà</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .main-content-inner,
        .widget-title,
        .widget-body,
        label,
        .btn {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
<div class="main-content">
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
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm"
                                           method="GET">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Tên tòa
                                                        nhà</label>
                                                        <%--<input type="text" id="name" class="form-control" name="name" value="${modelSearch.name}"/>--%>
                                                    <!-- name="name" hỗ trợ việc chúng ta tìm kiếm, gửi dữ liệu từ client về server thì nó nhận ra đc  -->
                                                    <form:input path="name" cssClass="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Diện tích
                                                        sàn</label>
                                                    <input type="number" id="floorArea" name="floorArea"
                                                           value="${modelSearch.floorArea}" class="form-control"/><br>
                                                        <%-- type="number" -> dùng kiểu bình thường --%>
                                                </div>
                                            </div>

                                            <div class="col-sm-2">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Quận hiện
                                                        có</label>
                                                    <select class="form-control" aria-label="Default select example">
                                                        <option selected>--Chọn quận--</option>
                                                        <option value="Q1">Quận 1</option>
                                                        <option value="Q2">Quận 2</option>
                                                        <option value="Q4">Quận 4</option>
                                                    </select><br>
                                                        <%--<form:select ></form:select>--%>
                                                </div>
                                            </div>

                                            <div class="col-sm-5">
                                                <div>
                                                    <label for="ward"
                                                           style="font-weight: bold; color: black;">Phường</label>
                                                    <form:input path="ward" cssClass="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-5">
                                                <div>
                                                    <label for="street"
                                                           style="font-weight: bold; color: black;">Đường</label>
                                                    <form:input path="street" cssClass="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Số tầng
                                                        hầm</label>
                                                    <input type="text" id="numberOfBasement" class="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="name"
                                                           style="font-weight: bold; color: black;">Hướng</label>
                                                    <input type="text" id="direction" class="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="name"
                                                           style="font-weight: bold; color: black;">Hạng</label>
                                                    <input type="text" id="level" class="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Diện tích
                                                        từ</label>
                                                    <input type="text" id="areaRentFrom" class="form-control"/><br>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Diện tích
                                                        đến</label>
                                                    <input type="text" id="areaRentTo" class="form-control"/><br>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Gía thuê
                                                        từ</label>
                                                    <input type="text" id="costRentFrom" class="form-control"/><br>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Gía thuê
                                                        đến</label>
                                                    <input type="text" id="costRentTo" class="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Tên quản
                                                        lí</label>
                                                    <input type="text" id="nameManage" class="form-control"/><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Điện
                                                        thoại quản lý</label>
                                                    <input type="text" id="phoneManage" class="form-control"/><br>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="name" style="font-weight: bold; color: black;">Chọn nhân
                                                        viên quản lý</label>
                                                    <select class="form-control" id="staffId"
                                                            aria-label="Default select example">
                                                        <option selected>--Chọn nhân viên phụ trách--</option>
                                                        <option value="1">One</option>
                                                        <option value="2">Two</option>
                                                        <option value="3">Three</option>
                                                    </select><br>
                                                </div>
                                            </div>

                                            <div class="col-sm-3">
                                                <input class="" type="checkbox" value="" id="checkbox1">
                                                <label style="font-weight: bold; color: black;">
                                                    Tầng trệt
                                                </label>

                                                <input class="" type="checkbox" value="" id="checkbox2" checked>
                                                <label style="font-weight: bold; color: black;">
                                                    Nguyên căn
                                                </label>

                                                <input class="" type="checkbox" value="" id="checkbox3" checked>
                                                <label style="font-weight: bold; color: black;">
                                                    Nội thất
                                                </label>
                                            </div>


                                            <div class="col-sm-12">
                                                <br>
                                                <button type="button" id="btnSearch" class="btn btn-sm btn-success">
                                                    Tìm kiếm
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                </button>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div><!-- /.row -->


                <div class="row">
                    <div class="col-xs-12">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success" data-toggle="tooltip"
                                    title="Thêm tòa nhà">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>

                            <button type="button" class="btn btn-danger " data-toggle="tooltip" \
                                    title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-12">
                        <table id="buildingList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên quản lí</th>
                                <th>Số điện thoại</th>
                                <th>D.T sàn</th>
                                <th>Gía thuê</th>
                                <th>Phí dịch vụ</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td><input type="checkbox" value="1" id="checkbox_1"></td>
                                <td>Building Tower</td>
                                <td>Quận Tân Bình</td>
                                <td>a Vinh</td>
                                <td>0123456789</td>
                                <td>150</td>
                                <td>145</td>
                                <td>45</td>
                                <td>
                                    <button class="btn btn-xs" data-toggle="tooltip"
                                            title="Giao tòa nhà" onclick="assingmentBuilding(1)">
                                        <i class="fa fa-home" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Giao tòa nhà cho nhân viên</h4>
            </div>

            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Choose Staff</th>
                        <th>Name Staff</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" value="1" id="customCheck1" checked></td>
                        <td>staff a</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="2" id="customCheck2" checked></td>
                        <td>staff b</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="3" id="customCheck3" checked></td>
                        <td>staff c</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" name="buildingId" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assingmentBuilding(buildingId) {
        openModalAssingmentBuilding();
        $("#buildingId").val(buildingId);
        console.log($("#buildingId").val());
    }

    function openModalAssingmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        var staffs = [];
        data['buildingId'] = $('#buildingId').val();
        // $('#staffList').find('tbody input[type=checkbox]:checked')
        // đoạn code này tương tự staff = [1,2,3], là 1 mảng chứa staff
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        assignStaff(data);
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api-user-assignment",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed')
                console.log(response)
            }
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['buildingIds'] = buildingIds; // push buildingIds vào data
        deleteBuilding(data);
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api-deleteBuilding",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed')
                console.log(response)
            }
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $("#listForm").submit();
    });
</script>
</body>
</html>
