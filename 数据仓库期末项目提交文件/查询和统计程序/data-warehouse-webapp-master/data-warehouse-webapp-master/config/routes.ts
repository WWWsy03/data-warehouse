/**
 * @name umi 的路由配置
 * @description 只支持 path,component,routes,redirect,wrappers,name,icon 的配置
 * @param path  path 只支持两种占位符配置，第一种是动态参数 :id 的形式，第二种是 * 通配符，通配符只能出现路由字符串的最后。
 * @param component 配置 location 和 path 匹配后用于渲染的 React 组件路径。可以是绝对路径，也可以是相对路径，如果是相对路径，会从 src/pages 开始找起。
 * @param routes 配置子路由，通常在需要为多个路径增加 layout 组件时使用。
 * @param redirect 配置路由跳转
 * @param wrappers 配置路由组件的包装组件，通过包装组件可以为当前的路由组件组合进更多的功能。 比如，可以用于路由级别的权限校验
 * @param name 配置路由的标题，默认读取国际化文件 menu.ts 中 menu.xxxx 的值，如配置 name 为 login，则读取 menu.ts 中 menu.login 的取值作为标题
 * @param icon 配置路由的图标，取值参考 https://ant.design/components/icon-cn， 注意去除风格后缀和大小写，如想要配置图标为 <StepBackwardOutlined /> 则取值应为 stepBackward 或 StepBackward，如想要配置图标为 <UserOutlined /> 则取值应为 user 或者 User
 * @doc https://umijs.org/docs/guides/routes
 */
export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './User/Login',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    name: 'time',
    icon: 'clockCircle',
    path: '/time',
    routes: [
      {
        path: '/time',
        component: './ByTime/ByTimeYear',
      },
      {
        path: '/time/year',
        name: 'year',
        component: './ByTime/ByTimeYear',
      },
      {
        path: '/time/year-month',
        name: 'year-month',
        component: './ByTime/ByTimeYearMonth',
      },
      {
        path: '/time/year-month-day',
        name: 'year-month-day',
        component: './ByTime/ByTimeYearMonthDay',
      },
      {
        path: '/time/year-season',
        name: 'year-season',
        component: './ByTime/ByTimeYearSeason',
      },
    ],
  },
  {
    name: 'movie',
    icon: 'fundProjectionScreen',
    path: '/movie',
    component: './ByMovie',
  },
  {
    name: 'director',
    icon: 'user',
    path: '/director',
    component: './ByDirector',
  },
  {
    name: 'actor',
    icon: 'user',
    path: '/actor',
    component: './ByActor',
  },
  {
    name: 'director-actor',
    icon: 'userSwitch',
    path: '/director-actor',
    routes: [
      {
        path: '/director-actor/actor-by-director',
        name: 'actor-by-director',
        component: './ByDirectorActor/ActorByDirector',
      },
      {
        path: '/director-actor/director-by-director',
        name: 'director-by-director',
        component: './ByDirectorActor/DirectorByDirector',
      },
      {
        path: '/director-actor/director-by-actor',
        name: 'director-by-actor',
        component: './ByDirectorActor/DirectorByActor',
      },
      {
        path: '/director-actor/actor-by-actor',
        name: 'actor-by-actor',
        component: './ByDirectorActor/ActorByActor',
      },
    ],
  },
  {
    name: 'category',
    icon: 'gold',
    path: '/category',
    component: './ByCategory',
  },
  {
    name: 'review',
    icon: 'like',
    path: '/review',
    routes: [
      {
        path: '/review/better-than',
        name: 'better',
        component: './ByReview/BetterGrade',
      },
      {
        path: '/review/positive',
        name: 'positive',
        component: './ByReview/PositiveReview',
      },
    ],
  },
  {
    name: 'relation',
    icon: 'usergroupAdd',
    path: '/relation',
    routes: [
      {
        path: '/relation/director-actor',
        name: 'director-actor',
        component: './ByRelation/DirectorActor',
      },
      {
        path: '/relation/director-director',
        name: 'director-director',
        component: './ByRelation/DirectorDirector',
      },
      {
        path: '/relation/actor-actor',
        name: 'actor-actor',
        component: './ByRelation/ActorActor',
      },
      {
        path: '/relation/hot-actor-actor',
        name: 'hot-actor-actor',
        component: './ByRelation/HotActorActor',
      },
    ],
  },
  {
    name: 'compose',
    icon: 'group',
    path: '/composed',
    routes: [
      {
        path: '/composed/year-type',
        name: 'year-type',
        component: './ByComposed/ByYearAndType',
      },
      {
        path: '/composed/year-director',
        name: 'year-director',
        component: './ByComposed/ByYearAndDirector',
      },
    ],
  },
  {
    name: 'trace',
    icon: 'history',
    path: '/trace',
    routes: [
      {
        path: '/trace/non-movie',
        name: 'non-movie',
        component: './Trace/NonMovie',
      },
      {
        path: '/trace/harry-potter',
        name: 'harry-potter',
        component: './Trace/HarryPotter',
      },
    ],
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    path: '*',
    layout: false,
    component: './404',
  },
]
