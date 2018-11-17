SELECT `a`.*,
	(SELECT `valueName` FROM `data_dictionary` `d` 
		WHERE `d`.`valueId`=`a`.`flatformId`
		 AND `d`.typeCode='APP_FLATFORM'  )
	AS `flatformName`,
	(SELECT `valueName` FROM `data_dictionary` `d` 
		WHERE `d`.`valueId`=`a`.`status`
		 AND `d`.typeCode='APP_STATUS')
	AS `statusName`,
	(SELECT `versionNo` FROM `app_version` `v` 
		WHERE `v`.`id`=`a`.`versionId`)
	AS `versionNo`,
	(SELECT `categoryName` FROM `app_category` `c` 
		WHERE `c`.`id`=`a`.`categoryLevel1`)
	AS `categoryLevel1Name`,
	(SELECT `categoryName` FROM `app_category` `c` 
		WHERE `c`.`id`=`a`.`categoryLevel2`)
	AS `categoryLevel2Name`,
	(SELECT `categoryName` FROM `app_category` `c` 
		WHERE `c`.`id`=`a`.`categoryLevel3`)
	AS `categoryLevel3Name`  
FROM `app_info` `a` ;