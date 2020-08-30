#/bin/bash

declare -A modelMap=(
	["gbCompany"]="GbCompany"
	["gbCompanyFavorite"]="GbCompanyFavorite"
	["gbCompanyLogistics"]="GbCompanyLogistics"
	["gbCompanyService"]="GbCompanyService"
	["gbSystem"]="GbSystem"
	["gbTheme"]="GbTheme"
	["sysMenu"]="SysMenu"
	["sysRole"]="SysRole"
	["sysRoleMenu"]="SysRoleMenu"
	["sysUser"]="SysUser"
	["sysUserRole"]="SysUserRole"
	["odrShoppingCart"]="OdrShoppingCart"
	["spCategory"]="SpCategory"
	["spGoodsColor"]="SpGoodsColor"
	["spGoodsSku"]="SpGoodsSku"
	["spGoodsComment"]="SpGoodsComment"
	["spGoodsCommentImg"]="SpGoodsCommentImg"
	["spGoods"]="SpGoods"
	["spGoodsDetail"]="SpGoodsDetail"
	["spGoodsFavorite"]="SpGoodsFavorite"
	["spGoodsImg"]="SpGoodsImg"
	["spGoodsParams"]="SpGoodsParams"
	["spGoodsParamsDetail"]="SpGoodsParamsDetail"
	["spGoodsVoucher"]="SpGoodsVoucher"
	["spVoucher"]="SpVoucher"
	["spVoucherReceive"]="SpVoucherReceive"
)

templateModel="GbCompany"
templateModelLow="gbCompany"
templateMapperFile="${templateModel}Mapper.java"



function createMapper(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create mapper"
		cp -rf ./template/$templateMapperFile ./mapper/"${modelMap[$key]}Mapper.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./mapper/"${modelMap[$key]}Mapper.java"
		sed -i "s@$templateModelLow@${key}@g" ./mapper/"${modelMap[$key]}Mapper.java"
	done
}

templateServiceFile="${templateModel}Service.java"
templateServiceImplFile="${templateModel}ServiceImpl.java"
function createService(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create service"
		cp -rf ./template/$templateServiceFile ./service/"${modelMap[$key]}Service.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./service/"${modelMap[$key]}Service.java"
		sed -i "s@$templateModelLow@${key}@g" ./service/"${modelMap[$key]}Service.java"

		echo "${modelMap[$key]}...create serviceImpl"
		cp -rf ./template/$templateServiceImplFile ./service/impl/"${modelMap[$key]}ServiceImpl.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./service/impl/"${modelMap[$key]}ServiceImpl.java"
		sed -i "s@$templateModelLow@${key}@g" ./service/impl/"${modelMap[$key]}ServiceImpl.java"
	done
}

templateControllerFile="${templateModel}FeignClient.java"
function createController(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create controller"
		cp -rf ./template/$templateControllerFile ./feign/"${modelMap[$key]}FeignClient.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./feign/"${modelMap[$key]}FeignClient.java"
		sed -i "s@$templateModelLow@${key}@g" ./feign/"${modelMap[$key]}FeignClient.java"

		_model=${templateModel,}
		_current=${modelMap[$key],}
		sed -i "s@$_model@$_current@g" ./feign/"${modelMap[$key]}FeignClient.java"
	done
}

templateApiFile="${templateModel}FeignApi.java"
function createApi(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create feignApi"
		cp -rf ./template/app/$templateApiFile ./api/"I${modelMap[$key]}FeignApi.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./api/"I${modelMap[$key]}FeignApi.java"
		sed -i "s@$templateModelLow@${key}@g" ./api/"I${modelMap[$key]}FeignApi.java"
	done
}


templateAppServiceFile="${templateModel}Service.java"
templateAppServiceImplFile="${templateModel}ServiceImpl.java"
function createAppService(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create service"
		cp -rf ./template/app/$templateAppServiceFile ./app/service/"${modelMap[$key]}Service.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./app/service/"${modelMap[$key]}Service.java"
		sed -i "s@$templateModelLow@${key}@g" ./app/service/"${modelMap[$key]}Service.java"

		echo "${modelMap[$key]}...create serviceImpl"
		cp -rf ./template/app/$templateAppServiceImplFile ./app/service/impl/"${modelMap[$key]}ServiceImpl.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./app/service/impl/"${modelMap[$key]}ServiceImpl.java"
		sed -i "s@$templateModelLow@${key}@g" ./app/service/impl/"${modelMap[$key]}ServiceImpl.java"
	done
}

templateAppControllerFile="${templateModel}Controller.java"
function createAppController(){
	for key in ${!modelMap[@]}
	do
		echo "${modelMap[$key]}...create controller"
		cp -rf ./template/$templateAppControllerFile ./controller/"${modelMap[$key]}Controller.java"
		sed -i "s@$templateModel@${modelMap[$key]}@g" ./controller/"${modelMap[$key]}Controller.java"
		sed -i "s@$templateModelLow@${key}@g" ./controller/"${modelMap[$key]}Controller.java"

		_model=${templateModel,}
		_current=${modelMap[$key],}
		sed -i "s@$_model@$_current@g" ./controller/"${modelMap[$key]}Controller.java"
	done
}



function createFolder(){
	mkdir -p ./mapper
	mkdir -p ./service
	mkdir -p ./service/impl
	#mkdir -p ./feign
	#mkdir -p ./api
	mkdir -p ./app/service
	mkdir -p ./app/service/impl
	mkdir -p ./app/controller
	mkdir -p ./controller
}


createFolder
createMapper
createService
#createController
#createAppService
#createApi
createAppController










