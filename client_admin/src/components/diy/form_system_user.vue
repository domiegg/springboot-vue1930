<template>
	<div>
			<el-form-item v-if="$check_register_field('add','user_name','/system_user/view')" label="用户姓名" prop="user_name">
				<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"></el-input>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','user_gender','/system_user/view')" label="用户性别" prop="user_gender">
				<el-select id="user_gender" v-model="form['user_gender']">
			<el-option v-for="o in list_user_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
				</el-form-item>
				<el-form-item v-if="$check_register_field('add','user_age','/system_user/view')" label="用户年龄" prop="user_age">
				<el-input id="user_age" v-model="form['user_age']" placeholder="请输入用户年龄"></el-input>
				</el-form-item>
	
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
							"user_name":  '' ,
								"user_gender":  '' ,
								"user_age":  '' ,
						}
				}
			},

		},
		data() {
			return {
				field: "system_user_id",
				url_add: "~/api/system_user/add?",
				url_set: "~/api/system_user/set?",
				url_upload: "~/api/system_user/upload?",

						list_user_gender: ['男','女'],
		
				rules: {
					"user_name": [    {required: true,message: '用户姓名不能为空'},  ],
					"user_gender": [    {required: true,message: '用户性别不能为空'},  ],
					"user_age": [    {required: true,message: '用户年龄不能为空'},  ],
				}

			}
		},
		methods: {

	
	
	

	
	
	

	
	
	

		},
		created() {
					}
	}
</script>
