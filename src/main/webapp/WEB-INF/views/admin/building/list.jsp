<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var = "buildingAPI" value ="/api/building"/>
<c:url var = "assignmentAPI" value ="/api/building/assignment-building"/>
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
                <c:if test="${null != messageResponse}">
                    <div class="alert alert-block alert-${alert}">
                        <button type="button" class="close" data-dismiss="alert">
                            <em class="ace-icon fa fa-times"></em>
                        </button>
                            ${messageResponse}
                    </div>
                </c:if>
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
                                                <form:input path="floorArea" cssClass="form-control"/>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-2">
                                            <div>
                                                <label><b>Quận hiện có</b></label>
                                                <form:select path="districtCode" cssClass="form-control">
                                                    <form:option value="">--- Chọn Quận ---</form:option>
                                                    <form:options items="${districtMap}" />
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
                                                    <form:option value="">--- Chọn nhân viên phụ trách ---</form:option>
                                                    <form:options items="${staffmaps}" />
                                                </form:select>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <label>Loại toà nhà</label>
                                            <c:forEach var="item" items="${buildingTypes}">
                                                <label class="checkbox-inline">
                                                    <form:checkbox path="types" value="${item.key}"/>${item.value}
                                                </label>
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
                                    title="Thêm tòa nhà" onclick="window.location.href='<c:url value="/admin/building-add"/>'">
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
                                <th class="center select-cell">
                                    <fieldset class="form-group"><input type="checkbox" id="checkAll" class="check-box-element"> </fieldset>
                                </th>
                                <th>Ngày</th>
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
                                    <td class="center select-cell">
                                        <fieldset><input type="checkbox" name="buildingIds"
                                                         value="${item.id}"
                                                         id="checkbox_${item.id}"
                                                         class="check-box-element"/></fieldset>
                                    </td>
                                    <td>${item.createdDate}</td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.managerName}</td>
                                    <td>${item.managerPhone}</td>
                                    <td>${item.floorArea}</td>
                                    <td>${item.rentAreaDescription}</td>
                                    <td>${item.rentPrice}</td>
                                    <td>${item.serviceFee}</td>
                                    <td>${item.brokerageFee}</td>
                                    <td>
                                        <button cclass="btn btn-xs btn-info" data-toggle="tooltip"
                                                title="Giao tòa nhà" id="btnBuildingAssignment" buildingId="${item.id}">
                                            <i class="fa fa-home" aria-hidden="true"></i>
                                        </button>
                                        <a class="btn btn-xs btn-dark" data-toggle="tooltip"
                                                title="Sửa thông tin toà nhà" value="${item.id}"
                                           href='<c:url value='/admin/building-edit-${item.id}'/>'>
                                            <i class="fa fa-edit" aria-hidden="true"></i>
                                            <%--<span><em class="ace-icon fa fa-pencil bigger-120"></em></span>--%>
                                        </a>
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding" data-dismiss="modal">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        const checkboxAll = $('#checkAll');
        const buildingItemsCheckbox = $('#buildingList input[type=checkbox][name="buildingIds"]');

        buildingItemsCheckbox.change(function () {
            const numberOfChecked = $('#buildingList input[type=checkbox][name="buildingIds"]:checked').length;
            const isCheckedAll = buildingItemsCheckbox.length === numberOfChecked;
            checkboxAll.prop('checked', isCheckedAll);

            if (1 <= numberOfChecked) {
                $('#btnDeleteBuilding').attr('disabled', false);
            } else {
                $('#btnDeleteBuilding').attr('disabled', true);
            }
        });
    });

    $(document).on("click", "#buildingList button#btnBuildingAssignment", function (e) {
        e.preventDefault();
        openModalAssignmentBuilding();
        let buildingId = $(this).attr('buildingId');
        $('#buildingId').val(buildingId);
        loadStaff(buildingId);
    })

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/"+buildingId+"/staffs",
            dataType: "json",
            success: function (response) {
                console.log('success');
                let row ='';
                $.each(response, function (index,item) {
                    row += '<tr>';
                    row += '<td class = "text-center"><input type="checkbox" value=' + item.staffId + ' id ="checkbox_' + item.staffId + '" class = "check-box-element" ' + item.checked+'/></td>';
                    row += '<td class = "text-center">' +item.fullName+'</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        let dataLength;
        let data = {};
        data['buildingId'] = $('#buildingId').val();
        data['staffIds'] = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        dataLength = data['staffIds'].length;
        showConfirmationAlertBeforeAction(function () {
            assignStaff(data);
        }, "Giao", "Giao ".concat(dataLength > 1 ? "các " : "", "nhân viên đã chọn quản lý tòa nhà này?"));
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/assignment-building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                showNotification('success', 'Giao tòa nhà cho nhân viên quản lý thành công!');
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        let buildingIds = $('#buildingList').find(
            'tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        showConfirmationAlertBeforeAction(function () {
            deleteBuilding(buildingIds);
        }, "Xóa", "Xóa ".concat(buildingIds > 1 ? "các " : "", "tòa nhà đã chọn?"));
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                window.location.href = "<c:url value ='/admin/building-list?message=delete_success'/>"
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
