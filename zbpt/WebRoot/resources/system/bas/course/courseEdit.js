function CourseEdit(config) {
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
						path : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '课程视频不能为空'
								}
							},
							file : {
								extension : 'mp4,rmvb,mkv,avi',
								type : 'video/mp4,video/rmvb,video/mkv,video/avi',
								message : '请重新选择课程视频'
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
	this.submitForm = function() {
		$("#courseForm").bootstrapValidator("validate"); // 验证

		if ($("#courseForm").data("bootstrapValidator").isValid()) {
			$("#courseForm").ajaxSubmit({
				url : "system/bas/course/edit.action",
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
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
		t.formValidatorInit();
		t.buttonBind();
		
		$("#level").change(function() {
			var checkValue = $("#level").val();
			var boxdata = new ComBoxData.courseTypeComBox(checkValue);
			console.log(boxdata);
			$("#type").empty();
			ComBoxData.autoCreateSelectOptions("type", ComBoxData.empty
							.concat(boxdata.data), null);
		});;
	}
}