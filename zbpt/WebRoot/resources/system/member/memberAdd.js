function MemberAdd(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#memberForm").bootstrapValidator({
					message : 'This value is not valid',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						name : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '姓名不能为空'
								},
								stringLength : {
									max : 6,
									message : '姓名不能大于6个字符'
								}
							}
						},
						cardnumber : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '身份证号不能为空'
								},
								stringLength : {
									max : 18,
									min : 18,
									message : '身份证号必须等于18位'
								}
							}
						},
						cardfaceimg : {
							group : '.col-xs-9',
							validators : {
							notEmpty : {
								message : '身份证正面照不能为空'
								}
							}
						},
						cardconimg : {
							group : '.col-xs-9',
							validators : {
							notEmpty : {
								message : '身份证反面照不能为空'
								}
							}
						},
						mobilephone : {
							group : '.col-xs-9',
							validators : {
							notEmpty : {
								message : '手机号不能为空'
								},
							stringLength : {
								max : 11,
								min : 11,
								message : '手机号必须为11位'}
							}
						},
					}
				});
	}

	// 提交数据
	this.submitForm = function() {
		$("#memberForm").bootstrapValidator("validate"); // 验证
		var pathFile = $('#pathFile').val();
		if (pathFile == '') {
			top.toastr.error('请上传文件');
			return;
		} else {
			name = pathFile.substring(pathFile.length - 4, pathFile.length);
			if (name == '.jpg' || name == 'jpeg') {

			} else {
				top.toastr.error('身份证正面照请上传jpg或者jpeg文件');
				return;
			}
		}
		var imgFile = $('#imgFile').val();
		if (imgFile == '') {
			top.toastr.error('请上传文件');
			return;
		} else {
			name = imgFile.substring(imgFile.length - 4, imgFile.length);
			if (name == '.jpg' || name == 'jpeg') {

			} else {
				top.toastr.error('身份证反面照请上传jpg或者jpeg文件');
				return;
			}
		}

		if ($("#memberForm").data("bootstrapValidator").isValid()) {
			$("#memberForm").ajaxSubmit({
				url : "system/sys/memberController/memberReg.action",
				type : "post",
				dataType : "json",
				beforeSend: function () {
					$('#submitForm').prop('disabled',true);
					},
				complete: function (){
					$('#submitForm').prop('disabled',false);
				},
				success : function(data) {
					debugger;
					if (data.execute) {
						top.toastr.success(data.msg);
						// closeParentLayer();
						parent.layer.closeAll('iframe');
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#memberForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#memberForm")[0][fieldName].value
											}
										}
									});
							$("#memberForm").data("bootstrapValidator")
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