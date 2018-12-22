function TeacherOpusAdd(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#teacherOpusForm").bootstrapValidator({
					message : 'This value is not valid',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						title : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '标题不能为空'
								},
								stringLength : {
									max : 20,
									message : '标题不能大于20个字符'
								}
							}
						},
						imgFile : {
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
						},
						pathFile : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '作品不能为空'
								}
							},
							file : {
								extension : 'avi,mov,mp4,rmvb',
								type : 'video/avi,video/mov,video/mp4,video/rmvb',
								message : '请重新选择作品'
							}
						}
						
					}
				});
	}

	// 提交数据
	// 提交数据
	this.submitForm = function() {
		$("#teacherOpusForm").bootstrapValidator("validate"); // 验证
		var imgFile = $('#imgFile').val();
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
		var pathFile = $('#pathFile').val();
		if (pathFile == '') {
			top.toastr.error('请上传作品');
			return;
		}

		if ($("#teacherOpusForm").data("bootstrapValidator").isValid()) {
			$("#teacherOpusForm").ajaxSubmit({
				url : "system/bas/teacherOpus/add.action",
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
							$("#teacherOpusForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#teacherOpusForm")[0][fieldName].value
											}
										}
									});
							$("#teacherOpusForm").data("bootstrapValidator")
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