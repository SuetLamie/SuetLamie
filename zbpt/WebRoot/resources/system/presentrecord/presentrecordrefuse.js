function PresentRecordEdit(config) {
	var t = this;

	// form验证初始化
	this.formValidatorInit = function() {
		$("#presentrecordForm").bootstrapValidator({
					message : 'This value is not valid',
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
				});
	}

	// 提交数据
	this.submitForm = function() {
		$("#presentrecordForm").bootstrapValidator("validate"); // 验证

		if ($("#presentrecordForm").data("bootstrapValidator").isValid()) {
			$("#presentrecordForm").ajaxSubmit({
				url : "system/bas/presentRecordController/refuse.action",
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data.execute) {
						top.toastr.success(data.msg);
						parent.layer.closeAll('iframe');
					} else {
						if (data.data) {
							var fieldName = data.data;
							$("#presentrecordForm").bootstrapValidator("addField",
									fieldName, {
										validators : {
											customValid : {
												message : data.msg,
												oldValue : $("#presentrecordForm")[0][fieldName].value
											}
										}
									});
							$("#presentrecordForm").data("bootstrapValidator")
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