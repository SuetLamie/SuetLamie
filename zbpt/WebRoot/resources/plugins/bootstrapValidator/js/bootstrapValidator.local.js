(function($) {
	$.fn.bootstrapValidator.i18n.customValid = $.extend($.fn.bootstrapValidator.i18n.customValid || {}, {
				'default' : 'Please enter a value'
			});
	$.fn.bootstrapValidator.validators.customValid = {
		enableByHtml5 : function($field) {
			var required = $field.attr('required') + '';
			return ('required' === required || 'true' === required);
		},
		validate : function(validator, $field, options) {
			var value = $field.val();
			if (value == options.oldValue) {
				return false;
			}
			return true;
		}
	};
}(window.jQuery));