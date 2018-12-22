function MemberEdit(config) {
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
						mobilePhone : {
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
						}
					}
				});
	}

	// 提交数据
	this.submitForm = function() {
		$("#memberForm").bootstrapValidator("validate"); // 验证

		if ($("#memberForm").data("bootstrapValidator").isValid()) {
			$("#memberForm").ajaxSubmit({
				url : "system/sys/memberController/updateMember.action",
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
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