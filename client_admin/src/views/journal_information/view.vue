<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','journal_number') || $check_field('add','journal_number') || $check_field('set','journal_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记编号" prop="journal_number">
												<el-input id="journal_number" v-model="form['journal_number']" placeholder="请输入日记编号"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_number')) || (!form['journal_information_id'] && $check_field('add','journal_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','journal_number')">{{form['journal_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_name') || $check_field('add','journal_name') || $check_field('set','journal_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记名称" prop="journal_name">
												<el-input id="journal_name" v-model="form['journal_name']" placeholder="请输入日记名称"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_name')) || (!form['journal_information_id'] && $check_field('add','journal_name'))" :disabled="disabledObj['journal_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','journal_name')">{{form['journal_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_label') || $check_field('add','journal_label') || $check_field('set','journal_label')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记标签" prop="journal_label">
												<el-input id="journal_label" v-model="form['journal_label']" placeholder="请输入日记标签"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_label')) || (!form['journal_information_id'] && $check_field('add','journal_label'))" :disabled="disabledObj['journal_label_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','journal_label')">{{form['journal_label']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_classification') || $check_field('add','journal_classification') || $check_field('set','journal_classification')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记分类" prop="journal_classification">
								<el-select id="journal_classification" v-model="form['journal_classification']"
						v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_classification')) || (!form['journal_information_id'] && $check_field('add','journal_classification'))">
						<el-option v-for="o in list_journal_classification" :key="o['journal_classification']" :label="o['journal_classification']"
							:value="o['journal_classification']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','journal_classification')">{{form['journal_classification']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_information') || $check_field('add','user_information') || $check_field('set','user_information')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户信息" prop="user_information">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_information(form['user_information']) }}
							<!--<el-input id="business_name" v-model="form['user_information']" placeholder="请输入用户信息"-->
							<!--v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','user_information')) || (!form['journal_information_id'] && $check_field('add','user_information'))" :disabled="disabledObj['user_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_information')">{{form['user_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','user_information')) || (!form['journal_information_id'] && $check_field('add','user_information'))" id="user_information" v-model="form['user_information']" :disabled="disabledObj['user_information_isDisabled']">
								<el-option v-for="o in list_user_user_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user_information')" id="user_information" v-model="form['user_information']" :disabled="true">
								<el-option v-for="o in list_user_user_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user_information" v-model="form['user_information']" :disabled="disabledObj['user_information_isDisabled']">
							<el-option v-for="o in list_user_user_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','user_name')) || (!form['journal_information_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_gender') || $check_field('add','user_gender') || $check_field('set','user_gender')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户性别" prop="user_gender">
												<el-input id="user_gender" v-model="form['user_gender']" placeholder="请输入用户性别"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','user_gender')) || (!form['journal_information_id'] && $check_field('add','user_gender'))" :disabled="disabledObj['user_gender_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_gender')">{{form['user_gender']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_age') || $check_field('add','user_age') || $check_field('set','user_age')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户年龄" prop="user_age">
												<el-input id="user_age" v-model="form['user_age']" placeholder="请输入用户年龄"
							  v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','user_age')) || (!form['journal_information_id'] && $check_field('add','user_age'))" :disabled="disabledObj['user_age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_age')">{{form['user_age']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','number_of_journals') || $check_field('add','number_of_journals') || $check_field('set','number_of_journals')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记数量" prop="number_of_journals">
								<el-select id="number_of_journals" v-model="form['number_of_journals']"
						v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','number_of_journals')) || (!form['journal_information_id'] && $check_field('add','number_of_journals'))">
						<el-option v-for="o in list_number_of_journals" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','number_of_journals')">{{form['number_of_journals']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_date') || $check_field('add','journal_date') || $check_field('set','journal_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记日期" prop="journal_date">
								<el-date-picker :disabled="disabledObj['journal_date_isDisabled']" v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_date')) || (!form['journal_information_id'] && $check_field('add','journal_date'))" id="journal_date"
						v-model="form['journal_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','journal_date')">{{form['journal_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_picture') || $check_field('add','journal_picture') || $check_field('set','journal_picture')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记图片" prop="journal_picture">
								<el-upload :disabled="disabledObj['journal_picture_isDisabled']" id="journal_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_journal_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_picture')) || (!form['journal_information_id'] && $check_field('add','journal_picture'))">
						<img v-if="form['journal_picture']" :src="$fullUrl(form['journal_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','journal_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['journal_picture'])" :preview-src-list="[$fullUrl(form['journal_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_audio') || $check_field('add','journal_audio') || $check_field('set','journal_audio')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记音频" prop="journal_audio">
												<el-upload v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_audio')) || (!form['journal_information_id'] && $check_field('add','journal_audio'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_journal_audio" :limit="1" accept="audio/ogg,audio/mp3,audio/wav">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将音频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','journal_audio')">
						<el-button type="primary" @click="download(form['journal_audio'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_video') || $check_field('add','journal_video') || $check_field('set','journal_video')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记视频" prop="journal_video">
												<el-upload v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_video')) || (!form['journal_information_id'] && $check_field('add','journal_video'))" class="upload-demo" drag
						action="" style="max-width: 300px;width: 100%;" :http-request="upload_journal_video" :limit="1" accept="video/ogg,video/mp4,video/webm">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
					</el-upload>
					<div v-else-if="$check_field('get','journal_video')">
						<el-button type="primary" @click="download(form['journal_video'])">下载<i
								class="el-icon-download el-icon--right"></i></el-button>
					</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','journal_content') || $check_field('add','journal_content') || $check_field('set','journal_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日记内容" prop="journal_content">
								<el-input type="textarea" id="journal_content" v-model="form['journal_content']" placeholder="请输入日记内容"
						v-if="user_group === '管理员' || (form['journal_information_id'] && $check_field('set','journal_content')) || (!form['journal_information_id'] && $check_field('add','journal_content'))" :disabled="disabledObj['journal_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','journal_content')">{{form['journal_content']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "journal_information_id",
				url_add: "~/api/journal_information/add?",
				url_set: "~/api/journal_information/set?",
				url_get_obj: "~/api/journal_information/get_obj?",
				url_upload: "~/api/journal_information/upload?",

				query: {
					"journal_information_id": 0,
				},

				form: {
								"journal_number": this.$get_stamp(), // 日记编号
										"journal_name":  '', // 日记名称
										"journal_label":  '', // 日记标签
										"journal_classification":  '', // 日记分类
										"user_information": 0, // 用户信息
										"user_name":  '', // 用户姓名
										"user_gender":  '', // 用户性别
										"user_age":  '', // 用户年龄
										"number_of_journals":  '', // 日记数量
										"journal_date":  '', // 日记日期
										"journal_picture":  '', // 日记图片
										"journal_audio":  '', // 日记音频
										"journal_video":  '', // 日记视频
										"journal_content":  '', // 日记内容
											"journal_information_id": 0, // ID
						
				},
				disabledObj:{
								"journal_number_isDisabled": false,
										"journal_name_isDisabled": false,
										"journal_label_isDisabled": false,
										"journal_classification_isDisabled": false,
										"user_information_isDisabled": false,
										"user_name_isDisabled": false,
										"user_gender_isDisabled": false,
										"user_age_isDisabled": false,
										"number_of_journals_isDisabled": false,
										"journal_date_isDisabled": false,
										"journal_picture_isDisabled": false,
										"journal_audio_isDisabled": false,
										"journal_video_isDisabled": false,
										"journal_content_isDisabled": false,
										},

	
		
		
						// 日记分类选项列表
				list_journal_classification: [""],
	
		
					// 用户列表
				list_user_user_information: [],
						// 用户组
				group_user_user_information: "",
				
		
		
						// 日记数量选项列表
				list_number_of_journals: ['1'],
	
		
		
		
		
		
	
			}
		},
		methods: {


	
	
			
	
			
	
			
				/**
			 * 获取日记分类列表
			 */
			async get_list_journal_classification() {
				var json = await this.$get("~/api/classification_management/get_list?");
				if(json.result && json.result.list){
					this.list_journal_classification = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
				/**
			 * 获取系统用户用户列表
			 */
			async get_list_user_user_information() {
                // if(this.user_group !== "管理员" && this.form["user_information"] === 0) {
                //     this.form["user_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=系统用户");
                if(json.result && json.result.list){
                    this.list_user_user_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取系统用户用户组
			 */
			async get_group_user_user_information() {
							this.form["user_information"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=系统用户");
				if(json.result && json.result.obj){
					this.group_user_user_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user_information.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["user_information"] = id
									_this.disabledObj['user_information' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "user_information") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_user_information(id){
				var obj = this.list_user_user_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
						/**
			 * 上传日记图片
			 * @param {Object} param 图片参数
			 */
			upload_journal_picture(param){
						this.uploadFile(param.file, "journal_picture");
					},
	
	
						/**
			 * 上传日记音频
			 * @param {Object} param 音频参数
			 */
			upload_journal_audio(param){
						this.uploadFile(param.file, "journal_audio");
					},
	
	
						/**
			 * 上传日记视频
			 * @param {Object} param 视频参数
			 */
			upload_journal_video(param){
						this.uploadFile(param.file, "journal_video");
					},
	
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
									
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
																				        if (this.form["journal_date"].indexOf("-")===-1){
          this.form["journal_date"] = this.$toTime(parseInt(this.form["journal_date"]),"yyyy-MM-dd")
        }
													$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																													        if(this.form["journal_date"]=="0000-00-00"){
          this.form["journal_date"] = null;
        }
				if(parseInt(this.form["journal_date"]) > 9999){
					this.form["journal_date"] = this.$toTime(parseInt(this.form["journal_date"]),"yyyy-MM-dd")
				}
														

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																																																return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/journal_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/journal_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/journal_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/journal_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/journal_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
										this.get_list_journal_classification();
							this.get_list_user_user_information();
					this.get_group_user_user_information();
																							},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
