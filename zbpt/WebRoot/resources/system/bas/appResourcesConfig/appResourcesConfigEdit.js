function AppResourcesConfigAddEdit(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#appResourcesConfigForm").bootstrapValidator({
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
									message : '名称不能为空'
								},
								stringLength : {
									max : 50,
									message : '名称不能大于50个字符'
								}
							}
						},
						level : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '优先级不能为空'
								}
							}
						},
						page : {
							group : '.col-xs-9',
							validators : {
								notEmpty : {
									message : '页面标记不能为空'
								}
							}
						}
					}
				});
	}

	// 提交数据
	this.submitForm = function() {
		$("#appResourcesConfigForm").bootstrapValidator("validate"); // 验证

		if ($("#appResourcesConfigForm").data("bootstrapValidator").isValid()) {
			$("#appResourcesConfigForm").ajaxSubmit({
				url : "system/bas/appresourcesconfig/edit.action",
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
						parent.layer.closeAll('iframe');
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#appResourcesConfigForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#appResourcesConfigForm")[0][fieldName].value
											}
										}
									});
							$("#appResourcesConfigForm").data("bootstrapValidator")
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