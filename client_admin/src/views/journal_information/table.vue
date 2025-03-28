<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>


										<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="日记名称">
									<el-input v-model="query.journal_name"></el-input>
								</el-form-item>
				</el-col>
												<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="日记分类">
									<el-select v-model="query.journal_classification">
											<el-option v-for="o in list_journal_classification" :key="o.journal_classification" :label="o.journal_classification"
								:value="o.journal_classification">
							</el-option>
										</el-select>
								</el-form-item>
				</el-col>
																																					<el-col :xs="24" :sm="10" :lg="8" class="search_btn_wrap_1">
					<el-form-item>
						<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;" class="search_btn_reset">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/journal_information/table','add') || $check_action('/journal_information/view','add')" class="el-button el-button--default el-button--primary search_btn_add" to="./view?">添加
						</router-link>
            			<el-button v-if="user_group == '管理员' || $check_action('/journal_information/table','del') || $check_action('/journal_information/view','del')" class="search_btn_del" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
				<el-table-column prop="journal_number" @sort-change="$sortChange" label="日记编号"
				v-if="user_group == '管理员' || $check_field('get','journal_number')" min-width="200">
					</el-table-column>
					<el-table-column prop="journal_name" @sort-change="$sortChange" label="日记名称"
				v-if="user_group == '管理员' || $check_field('get','journal_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="journal_label" @sort-change="$sortChange" label="日记标签"
				v-if="user_group == '管理员' || $check_field('get','journal_label')" min-width="200">
					</el-table-column>
					<el-table-column prop="journal_classification" @sort-change="$sortChange" label="日记分类"
				v-if="user_group == '管理员' || $check_field('get','journal_classification')" min-width="200">
					</el-table-column>
					<el-table-column prop="user_information" @sort-change="$sortChange" label="用户信息"
				v-if="user_group == '管理员' || $check_field('get','user_information')" min-width="200">
						<template slot-scope="scope">
					{{ get_user_user_information(scope.row['user_information']) }}
				</template>
					</el-table-column>
					<el-table-column prop="user_name" @sort-change="$sortChange" label="用户姓名"
				v-if="user_group == '管理员' || $check_field('get','user_name')" min-width="200">
					</el-table-column>
					<el-table-column prop="user_gender" @sort-change="$sortChange" label="用户性别"
				v-if="user_group == '管理员' || $check_field('get','user_gender')" min-width="200">
					</el-table-column>
					<el-table-column prop="user_age" @sort-change="$sortChange" label="用户年龄"
				v-if="user_group == '管理员' || $check_field('get','user_age')" min-width="200">
					</el-table-column>
					<el-table-column prop="number_of_journals" @sort-change="$sortChange" label="日记数量"
				v-if="user_group == '管理员' || $check_field('get','number_of_journals')" min-width="200">
					</el-table-column>
					<el-table-column prop="journal_date" @sort-change="$sortChange" label="日记日期"
				v-if="user_group == '管理员' || $check_field('get','journal_date')" min-width="200">
		                <template slot-scope="scope">
                	{{ $toTime(scope.row["journal_date"],"yyyy-MM-dd") }}
                </template>
					</el-table-column>
					<el-table-column prop="journal_picture" @sort-change="$sortChange" label="日记图片"
				v-if="user_group == '管理员' || $check_field('get','journal_picture')" min-width="200">
						<template slot-scope="scope">
					<el-image style="width: 100px; height: 100px" :src="$fullUrl(scope.row['journal_picture'])"
						:preview-src-list="[$fullUrl(scope.row['journal_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</template>
					</el-table-column>
					<el-table-column prop="journal_audio" @sort-change="$sortChange" label="日记音频"
				v-if="user_group == '管理员' || $check_field('get','journal_audio')" min-width="200">
						<template slot-scope="scope">
					<a href="javascript:void(0)" @click="journal_audioAudioPlay($fullUrl(scope.row['journal_audio']))" >点此播放</a>
				</template>
					</el-table-column>
					<el-table-column prop="journal_video" @sort-change="$sortChange" label="日记视频"
				v-if="user_group == '管理员' || $check_field('get','journal_video')" min-width="200">
						<template slot-scope="scope">
					<a href="javascript:void(0)" @click="journal_videoVideoPlay($fullUrl(scope.row['journal_video']))" >点此播放</a>
				</template>
					</el-table-column>
					<el-table-column prop="journal_content" @sort-change="$sortChange" label="日记内容"
				v-if="user_group == '管理员' || $check_field('get','journal_content')" min-width="200">
					</el-table-column>
	



            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>







			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/journal_information/table','set') || $check_action('/journal_information/view','set') || $check_action('/journal_information/view','get') || $check_action('//table','add') || $check_action('//view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/journal_information/table','set') || $check_action('/journal_information/view','set') || $check_action('/journal_information/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

															<el-dialog
					title="音频详情"
					:visible.sync="journal_audioAudioPlayerModalVisible"
					width="30%"
			>
				<audio id="journal_audioAudioPlayer" :src="journal_audioAudioUrl" autoplay="autoplay" width="100%" controls>
					您的浏览器不支持Audio标签。
				</audio>
				<span slot="footer" class="dialog-footer">
					<el-button @click="journal_audioCancelAudioModalVisible">取 消</el-button>
					</span>
			</el-dialog>
					<el-dialog
					title="视频详情"
					:visible.sync="journal_videoPlayerModalVisible"
					width="30%"
			>
				<video id="journal_videoPlayer" :src="journal_videoVideoUrl" autoplay="autoplay" width="100%" controls>
					您的浏览器不支持Video标签。
				</video>
				<span slot="footer" class="dialog-footer">
				<el-button @click="journal_videoCancelModalVisible">取 消</el-button>
				</span>
			</el-dialog>
		
		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/journal_information/get_list?like=0",
				url_del: "~/api/journal_information/del?",

				// 字段ID
				field: "journal_information_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
									"journal_name": "",
												"journal_classification": "",
																		"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},

				// 数据
				list: [],
																journal_audioAudioPlayerModalVisible:false,
				journal_audioAudioUrl:"",
						journal_videoPlayerModalVisible:false,
				journal_videoVideoUrl:"",
													// 日记分类列表
				list_journal_classification: [""],
										// 用户列表
				list_user_user_information: [],
												// 日记数量列表
				list_number_of_journals: ['1'],
															}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},

			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
						let sqlwhere = "(";
																														if(user_group=="系统用户"){
						sqlwhere+= "`user_information` = " + this.user.user_id + " or ";
					}
																																																							if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						param["sqlwhere"] = sqlwhere;
					}
					}
				return param;
			},

										/**
			 * 获取日记分类列表
			 */
			async get_list_journal_classification() {
				var json = await this.$get("~/api/classification_management/get_list?");
				if(json.result){
					this.list_journal_classification = json.result.list;
				}else if (json.error){
					console.log(json.error);
				}
			},
																						
			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [
																		];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},




												/**
			 * 获取系统用户用户列表
			 */
			async get_list_user_user_information() {
				var json = await this.$get("~/api/user/get_list?user_group=系统用户");
				if(json.result && json.result.list){
					this.list_user_user_information = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			get_user_user_information(id){
				var obj = this.list_user_user_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
																			journal_audioAudioPlay(v) {
				this.journal_audioAudioUrl = v
				this.journal_audioAudioPlayerModalVisible = true
			},

			journal_audioCancelAudioModalVisible(){
				this.journal_audioAudioUrl = ""
				this.journal_audioAudioPlayerModalVisible = false
			},
						journal_videoVideoPlay(v) {
				this.journal_videoVideoUrl = v
				this.journal_videoPlayerModalVisible = true
			},

			journal_videoCancelModalVisible(){
				this.journal_videoVideoUrl = ""
				this.journal_videoPlayerModalVisible = false
			},
						deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
										// 初始化日记分类列表
			this.get_list_journal_classification();
									this.get_list_user_user_information();
																						setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>
