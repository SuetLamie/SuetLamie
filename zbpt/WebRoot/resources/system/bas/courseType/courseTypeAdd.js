function CourseTypeAdd(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#courseTypeForm").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				dict_parent_id : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '课程类别不能为空'
						}
					}
				},
				dict_detail_name : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '子类别名称不能为空'
						}
					}
				}
			}
		});
	}

	// 提交数据
	// 提交数据
	this.submitForm = function() {
		$("#courseTypeForm").bootstrapValidator("validate"); // 验证

		if ($("#courseTypeForm").data("bootstrapValidator").isValid()) {
			$("#courseTypeForm").ajaxSubmit({
				url : "system/bas/coursetype/add.action",
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
							$("#courseTypeForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#courseTypeForm")[0][fieldName].value
											}
										}
									});
							$("#courseTypeForm").data("bootstrapValidator")
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