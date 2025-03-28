import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
import register from '../views/register.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 注册
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {
			index: 0,
			title: '注册'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},








	// 系统用户路由
	{
		path: '/system_user/table',
		name: 'system_user_table',
		component: () => import('../views/system_user/table.vue'),
		meta: {
			index: 0,
			title: '系统用户列表'
		}
	},
	{
		path: '/system_user/view',
		name: 'system_user_view',
		component: () => import('../views/system_user/view.vue'),
		meta: {
			index: 0,
			title: '系统用户详情'
		}
	},
	// 分类管理路由
	{
		path: '/classification_management/table',
		name: 'classification_management_table',
		component: () => import('../views/classification_management/table.vue'),
		meta: {
			index: 0,
			title: '分类管理列表'
		}
	},
	{
		path: '/classification_management/view',
		name: 'classification_management_view',
		component: () => import('../views/classification_management/view.vue'),
		meta: {
			index: 0,
			title: '分类管理详情'
		}
	},
	// 日记信息路由
	{
		path: '/journal_information/table',
		name: 'journal_information_table',
		component: () => import('../views/journal_information/table.vue'),
		meta: {
			index: 0,
			title: '日记信息列表'
		}
	},
	{
		path: '/journal_information/view',
		name: 'journal_information_view',
		component: () => import('../views/journal_information/view.vue'),
		meta: {
			index: 0,
			title: '日记信息详情'
		}
	},
	// 备忘录信息路由
	{
		path: '/memorandum_information/table',
		name: 'memorandum_information_table',
		component: () => import('../views/memorandum_information/table.vue'),
		meta: {
			index: 0,
			title: '备忘录信息列表'
		}
	},
	{
		path: '/memorandum_information/view',
		name: 'memorandum_information_view',
		component: () => import('../views/memorandum_information/view.vue'),
		meta: {
			index: 0,
			title: '备忘录信息详情'
		}
	},
	// 提醒信息路由
	{
		path: '/reminder_information/table',
		name: 'reminder_information_table',
		component: () => import('../views/reminder_information/table.vue'),
		meta: {
			index: 0,
			title: '提醒信息列表'
		}
	},
	{
		path: '/reminder_information/view',
		name: 'reminder_information_view',
		component: () => import('../views/reminder_information/view.vue'),
		meta: {
			index: 0,
			title: '提醒信息详情'
		}
	},
	// 公告信息路由
	{
		path: '/announcement_information/table',
		name: 'announcement_information_table',
		component: () => import('../views/announcement_information/table.vue'),
		meta: {
			index: 0,
			title: '公告信息列表'
		}
	},
	{
		path: '/announcement_information/view',
		name: 'announcement_information_view',
		component: () => import('../views/announcement_information/view.vue'),
		meta: {
			index: 0,
			title: '公告信息详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "网络云端日记本系统-admin";
	document.title = title;
})

export default router
