<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%--<c:url var="buildingListURL" value="/admin/building-edit"/>--%>
<c:url var="buildingAPI" value="/api/building"  />
<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
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
                <li class="active">Building Edit</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" role="form" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name"> Tên tòa nhà </label>
                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" name="name" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Phường </label>
                            <div class="col-sm-9">
                                <input type="number" id="ff" class="form-control" name="ward"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Đường </label>
                            <div class="col-sm-9">
                                <input type="number" id="o" class="form-control" name="street"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Kết cấu </label>
                            <div class="col-sm-9">
                                <input type="number" id="n" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="numberOfBasement"> Số tầng
                                hầm </label>
                            <div class="col-sm-9">
                                <input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement"
                                       value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Diện tích sàn </label>
                            <div class="col-sm-9">
                                <input type="number" id="m" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Hướng </label>
                            <div class="col-sm-9">
                                <input type="text" id="l" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Hạng </label>
                            <div class="col-sm-9">
                                <input type="text" id="k" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Diện tích thuê </label>
                            <div class="col-sm-9">
                                <input type="number" id="h" class="form-control" name=""/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Mô tả diện tích </label>
                            <div class="col-sm-9">
                                <input type="text" id="g" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Gía thuê </label>
                            <div class="col-sm-9">
                                <input type="number" id="f" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Mô tả giá </label>
                            <div class="col-sm-9">
                                <input type="text" id="e" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Phí dịch vụ </label>
                            <div class="col-sm-9">
                                <input type="number" id="d" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Phí ngoài giờ </label>
                            <div class="col-sm-9">
                                <input type="number" id="c" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Tiền điện </label>
                            <div class="col-sm-9">
                                <input type="number" id="b" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Phí dịch vụ </label>
                            <div class="col-sm-9">
                                <input type="number" id="a" class="form-control" name=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for=""> Ghi chú </label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="" rows="6" name=""></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding" name="">Add Building
                                </button>
                                <button type="button" class="btn btn-danger" id="btnRemove" name="">Remove</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div><!-- /.row -->
        </div> <!--page-content -->
    </div>
</div><!-- /.main-content -->


<script>
    //btnAddBuilding
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        //  call api add building function
        // t1. cái pthuc đẩy về là : POST
        // t2. nhập đoạn url vô
        // t3. cái dữ liệu mình đẩy vô là chuỗi json - cần phải định nghĩa (chuỗi: text/plain / json: json)
        var data = {};
        var buildingTypes = [];
        var formData = $('#formEdit').serializeArray();

        $.each(formData, function (index, v) {
            //buildingTypes.push(v.value);
            data["" + v.name + ""] = v.value;
        });
        data["buildingTypes"] = buildingTypes;

        $.ajax({
            type: 'POST',
            url: "/api/building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",

            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    });
</script>

</body>
</html>