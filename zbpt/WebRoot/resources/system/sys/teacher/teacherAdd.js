function TeacherAdd(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#teacherForm").bootstrapValidator({
					message : 'This value is not valid',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						nickname : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '名称不能为空'
								},
								stringLength : {
									max : 10,
									message : '名称不能大于10个字符'
								}
							}
						},
					
						picture : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '封面不能为空'
								}
							},
							file : {
								extension : 'png,jpg,jpeg',
								type : 'image/png,image/jpg,image/jpeg',
								message : '请重新选择封面'
							}
						}
					}
				});
	}

	// 提交数据
	// 提交数据
	this.submitForm = function() {
		$("#teacherForm").bootstrapValidator("validate"); // 验证
		var imgFile = $('#pathFile').val();
		if (imgFile == '') {
			top.toastr.error('请上传文件');
			return;
		} else {
			name = imgFile.substring(imgFile.length - 4, imgFile.length);
			if (name == '.jpg' || name == 'jpeg') {

			} else {
				top.toastr.error('封面请上传jpg或者jpeg文件');
				return;
			}
		}

		if ($("#teacherForm").data("bootstrapValidator").isValid()) {
			$("#teacherForm").ajaxSubmit({
				url : "system/sys/teacher/add.action",
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
						// closeParentLayer();
						parent.layer.closeAll('iframe');
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#teacherForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#teacherForm")[0][fieldName].value
											}
										}
									});
							$("#teacherForm").data("bootstrapValidator")
									.validateField(fieldName);
						} else {
							top.toastr.error(data.msg);
						}
					}
				}

			});

		}
	}

	this.closeLayer = function() {
		parent.layer.closeAll('iframe');
	};

	this.buttonBind = function() {
		$("#submitForm").click(function() {
					t.submitForm();
				});
	};

	// 初始化
	this.init = function() {
		t.formValidatorInit();
		t.buttonBind();
	}
}