<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                        <h4 class="widget-title">Tìm tòa nhà</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
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
                                                <label><b>Tên tòa nhà</b></label>
                                                <form:input path="name" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div>
                                                <label><b>Diện tích sàn</b></label>
                                                <form:input path="floorArea" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-2">
                                            <div>
                                                <label><b>Quận hiện có</b></label>
                                                <form:select path="districtCode" cssClass="form-control">
                                                    <option selected value="">Chọn Quận</option>
                                                    <c:forEach var="item" items="${districts}">
                                                        <form:option value="${item.code}">${item.name}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-5">
                                            <div>
                                                <label><b>Phường</b></label>
                                                <form:input path="ward" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-5">
                                            <div>
                                                <label><b>Đường</b></label>
                                                <form:input path="street" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Số tầng hầm</b></label>
                                                <form:input path="numberOfBasement" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Hướng</b></label>
                                                <form:input path="direction" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Hạng</b></label>
                                                <form:input path="level" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Diện tích từ</b></label>
                                                <form:input path="rentAreaFrom" cssClass="form-control"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Diện tích đến</b></label>
                                                <form:input path="rentAreaTo" cssClass="form-control"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Giá thuê từ</b></label>
                                                <form:input path="rentPriceFrom" cssClass="form-control"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Giá thuê đến</b></label>
                                                <form:input path="rentPriceTo" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Tên quản lí</b></label>
                                                <form:input path="managerName" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Điện thoại quản lý</b></label>
                                                <form:input path="managerPhone" cssClass="form-control"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Chọn nhân viên quản lý</b></label>
                                                <form:select path="staffId" class="form-control">
                                                    <option selected value="">Chọn nhân viên quản lí</option>
                                                    <c:forEach var="item" items="${staffmaps}">
                                                        <form:option value="${item.id}">${item.fullName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <c:forEach var="item" items="${buildingTypes}">
                                                <input type="checkbox" id="type_${item.code}" name="types" value="${item.code}" />
                                                <label for="type_${item.code}" style="font-weight: bold; margin-right: 10px; display: inline-block">${item.name}</label>
                                            </c:forEach>
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

                </div><!-- /.row -->

                <div class="row">
                    <div class="col-xs-12">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success" data-toggle="tooltip"
                                    title="Thêm tòa nhà" onclick="window.location.href='<c:url value="/admin/building-edit"/>'">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>

                            <button type="button" class="btn btn-danger btn-bold" data-toggle="tooltip"
                                    title="Xóa tòa nhà" id="btnDeleteBuilding" onclick="warningDelete()">
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
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="checkItem" id="selectAll" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên quản lí</th>
                                <th>Số điện thoại</th>
                                <th>Diện tích sàn</th>
                                <th>Giá thuê</th>
                                <th>Phí dịch vụ</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${buildings}">
                                <tr>
                                    <td> <input type="checkbox" value="${item.id}" name="checkBuildings[]"></td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.managerName}</td>
                                    <td>${item.managerPhone}</td>
                                    <td>${item.floorArea}</td>
                                    <td>${item.rentPrice}</td>
                                    <td>${item.serviceFee}</td>
                                    <td>
                                        <button class="btn btn-xs" data-toggle="tooltip"
                                                title="Giao tòa nhà" value="${item.id}" onclick="assignmentBuilding(value)">
                                            <i class="fa fa-home" aria-hidden="true"></i>
                                        </button>
                                        <button class="btn btn-xs btn-dark" data-toggle="tooltip"
                                                title="Sửa thông tin toà nhà" value="${item.id}"
                                                onclick="editBuilding(value)">
                                            <i class="fa fa-edit" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
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
                <form>
                    <table class="table table-bordered" id="staffList">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" id="selectAll2" class="ace"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="text-center">Tên nhân viên</th>
                        </tr>
                        </thead>
                        <tbody id="dsnv">
                        </tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="assignment" data-dismiss="modal">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    var buildingid;
    function assignmentBuilding(value) {
        buildingid = value;
        $("#dsnv").empty();
        $.ajax({
            type: "GET",
            url: "<c:url value='/api/building'/>" + '/' + value + '/staff',
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var arrBuilding = response;
                arrBuilding.forEach(function(item) {
                    var newRow = '<tr>'
                        +  '<td class=text-center>' +
                        '<input type="checkbox" ' + item.checked + ' name="checkStaffs[]" value="' + item.id  + '" />'
                        +  '</td>'
                        +  '<td>'+ item.fullName + '</td>' +
                        '</tr>';
                    $("#dsnv").append(newRow);
                });
            },
            error: function (response) {
                console.log('failed')
                console.log(response)
            }
        });
        openModalAssignmentBuilding();
    }

    $("#assignment").click(function (e) {
        e.preventDefault();
        var values = [];
        var data = {};
        var checkData = $('input[name="checkStaffs[]"]:checked')

        $.each(checkData, function () {
            values.push($(this).val());
        });

        data["staffIds"] = values;

        $.ajax({
            type: "POST",
            url: '<c:url value="/api/building"/>' + '/' + buildingid + '/assignment',
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
                window.location.reload();
            },
            error: function (response) {
                alert("fail")
                console.log(response)
            }
        });
    })

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    function warningDelete() {
        showAlertBeforeDelete(function () {
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuilding(dataArray);
        });
        e.preventDefault();
    }
    function deleteBuilding(data) {
        $.ajax({
            url: '<c:url value="/api/building"/>',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/building-list?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building-list?message=error_system'/>";
            }
        });
    }


    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $("#listForm").submit();
    });
    function editBuilding(value) {
        window.location.href = '<c:url value="/admin/building-edit" />' + '?buildingid=' + value;
    }


    $('#selectAll').change(function() {
        $('input[name="checkBuildings[]"]').prop('checked', this.checked);
    });

    $("#selectAll2").click(function () {
        $('input[name="checkStaffs[]"]').prop('checked', this.checked);
    })

    var valueType = ${modelSearch.types} + '';
    if (valueType != '') {
        $.each(valueType, function (index, value) {
            $("#rent[value='" + value + "']").prop('checked', true);
        });
    }

</script>
</body>
</html>
