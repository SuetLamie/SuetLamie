function CourseAdd(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#courseForm").bootstrapValidator({
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
									message : '课程标题不能为空'
								},
								stringLength : {
									max : 20,
									message : '课程标题不能大于20个字符'
								}
							}
						},
						level : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '课程类别不能为空'
								}
							}
						},
						type : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '课程子类别不能为空'
								}
							}
						},
						img : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '课程封面不能为空'
								}
							},
							file : {
								extension : 'png,jpg,jpeg',
								type : 'image/png,image/jpg,image/jpeg',
								message : '请重新选择课程封面'
							}
						},
						ji_fen : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '积分不能为空'
								}
							}
						},
						jia_ge : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '价格不能为空'
								}
							}
						}
					}
				});
	}

	// 提交数据
	// 提交数据
	this.submitForm = function() {
		$("#courseForm").bootstrapValidator("validate"); // 验证
		var imgFile = $('#imgFile').val();
		if (imgFile == '') {
			top.toastr.error('请上传文件');
			return;
		} else {
			name = imgFile.substring(imgFile.length - 4, imgFile.length);
			if (name == '.jpg' || name == 'jpeg') {

			} else {
				top.toastr.error('课程封面请上传jpg或者jpeg文件');
				return;
			}
		}

		if ($("#courseForm").data("bootstrapValidator").isValid()) {
			$("#courseForm").ajaxSubmit({
				url : "system/bas/course/add.action",
				type : "post",
				dataType : "json",
				beforeSend : function() {
					$("#upload_tip").show();
					$("#submitForm").attr('disabled',true);
				},
				complete : function() {
					$("#upload_tip").hide();
					$("#submitForm").attr('disabled',false);
				},
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
						// closeParentLayer();
						parent.layer.closeAll('iframe');
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#courseForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#courseForm")[0][fieldName].value
											}
										}
									});
							$("#courseForm").data("bootstrapValidator")
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
		$("#upload_tip").hide();
		t.formValidatorInit();
		t.buttonBind();

		$("#level").change(function() {
			var checkValue = $("#level").val();
			var boxdata = new ComBoxData.courseTypeComBox(checkValue);
			console.log(boxdata);
			$("#type").empty();
			ComBoxData.autoCreateSelectOptions("type",
					ComBoxData.empty.concat(boxdata.data), null);
		});
	}
}