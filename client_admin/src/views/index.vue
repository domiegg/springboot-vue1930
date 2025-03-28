<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
								<el-col v-if="user_group == '管理员' || $check_figure('/journal_information/table')" :span="8">
						<div class="card chart">
									<newLineChart v-if="line_obj_journal_information.values.length > 0" id="line_obj_journal_information" :vm="line_obj_journal_information" :title="'日记信息统计'">
							</newLineChart>
							<div v-if="!line_obj_journal_information.values.length">日记信息没有符合条件的数据</div>
								</div>
					</el-col>
								</el-row>


			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				activeName: "third",
									line_obj_journal_information:{
					names:[],
					xAxis: [],
					values:[]
				},
											url_user_count: "~/api/user/count?",
			};
		},
		created() {
						// 执行日记信息数据获取
			this.get_list_journal_information();
						},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
								// 获取日记信息统计折线图
			async get_list_journal_information() {
																								let group_by_value = "journal_classification";
				let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="系统用户"){
						sqlwhere+= "`user_information` = " + user_id + " or ";
					}
																																																																																																																																							if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/journal_information/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].journal_classification);
							}
							this.line_obj_journal_information.names = name_list;
							this.get_list_journal_information_sub("journal_classification",flag);
						}
				});
																																																										},
			async get_list_journal_information_sub(v1,names_flag) {
																																																						let data = {};
								let flag = false;
												let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																						if (user_group=="系统用户"){
						sqlwhere+= "`user_information` = " + user_id + " or ";
					}
																																																																																																																																							if (sqlwhere.length>1){
						sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
						sqlwhere += ")";
						data.sqlwhere = sqlwhere;
					}
									}
								await this.$get(
					"~/api/journal_information/get_list?groupby=journal_date",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
												xAxis_list.push(this.$toTime(list[i].journal_date ,"yyyy-MM-dd"));
											}
							this.line_obj_journal_information.xAxis = xAxis_list;
							this.get_list_journal_information_sub_sub(v1,"journal_date",names_flag,flag);
						}
				});
																												},
			async get_list_journal_information_sub_sub(v1,v2,names_flag,xAxis_flag) {
																																																	let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
								let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
										let sqlwhere = "(";
																																																																							if (user_group=="系统用户"){
							sqlwhere+= "`user_information` = " + user_id + " or ";
						}
																																																																																																																																								if (sqlwhere.length>1){
							sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
							sqlwhere += ")";
							data.sqlwhere = sqlwhere;
						}
									}
								for (let i=0;i<this.line_obj_journal_information.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_journal_information.names.length;j++){
						data[v2] = this.line_obj_journal_information.xAxis[i];
						data[v1] = this.line_obj_journal_information.names[j];
						await this.$get(
								"~/api/journal_information/sum?field=number_of_journals",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_journal_information.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_journal_information.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_journal_information.xAxis,true);
				}
																																	},
							
		},
		computed:{
			recognitionHeight(){
				return "830px"
			},
			recognitionUrl(){
				return "https://www.faceplusplus.com.cn/${model.filter.recognitionType}/"
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}

	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
	.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
		width: 100%;
		height: 100%;
	}
	.iframe_box_top{
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100px;
		font-size: 25px;
		line-height: 100px;
		background: #fff;
		z-index: 99999999;
		padding-left: 50px;
	}
	#iframe_box_face div::before {
		content: '';
		width: 100px;
		position: absolute;
		top: 154px;
		right: 129px;
		z-index: 999;
		height: 20px;
		background-color: #FFFFFF;
	}

	#iframe_box_face>h1 {
		margin-top: 100px;
		margin-bottom: 20px;
	}
</style>
