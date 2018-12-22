function CoursewareAdd(config) {
	var t = this;
	this.page_index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引

	// form验证初始化
	this.formValidatorInit = function() {
		$("#coursewareAddForm").bootstrapValidator({
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
									message : '课件名称不能为空'
								},
								stringLength : {
									max : 20,
									message : '课件名称不能大于20个字符'
								}
							}
						},
						imgFile : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '默认图片不能为空'
								}
							},
							file : {
								extension : 'png,jpg,jpeg',
								type : 'image/png,image/jpg,image/jpeg',
								message : '请重新选择默认图片'
							}
						},
						pathFile : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '课件不能为空'
								}
							},
							file : {
								extension : 'avi,mov,mp4,rmvb',
								type : 'video/avi,video/mov,video/mp4,video/rmvb',
								message : '请重新选择课件'
							}
						}
					}
				});
	}

	// 提交数据
	this.submitForm = function() {
		$("#coursewareAddForm").bootstrapValidator("validate"); // 验证
		var pathFile = $('#pathFile').val();
		if (pathFile == '') {
			top.toastr.error('请上传课件文件');
			return;
		}
		var imgFile = $('#imgFile').val();
		if (imgFile == '') {
			top.toastr.error('请上传默认图片文件');
			return;
		}
		if ($("#coursewareAddForm").data("bootstrapValidator").isValid()) {
			$("#coursewareAddForm").ajaxSubmit({
				url : "system/bas/courseware/add.action",
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
						parent.layer.close(t.page_index); // 再执行关闭
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#coursewareAddForm").bootstrapValidator(
									"addField", fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#coursewareAddForm")[0][fieldName].value
											}
										}
									});
							$("#coursewareAddForm").data("bootstrapValidator")
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
		parent.layer.close(t.page_index); // 再执行关闭
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
	}
}