package com.example.asus_cp.wanandroid.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.main.MainPagerAdapter;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.itemDeraction.MyItemDecoration;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainPagerFragment extends BaseFragment<MainPagerContract.Presenter> implements MainPagerContract.View{

    @BindView(R.id.recyler_view)
    RecyclerView recyclerView;

    @Inject
    Gson gson;

    private MainPagerAdapter adapter;

    private List<MainPagerListBean.DataBean.DatasBean> datas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    private void init() {
        datas = new ArrayList<>();
        //列表数据
        String s = "{\"data\":{\"curPage\":2,\"datas\":[{\"apkLink\":\"\",\"author\":\"鸿洋\",\"chapterId\":298,\"chapterName\":\"我的博客\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2830,\"link\":\"http://www.wanandroid.com/blog/show/2113\",\"niceDate\":\"2018-04-18\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1524062149000,\"superChapterId\":298,\"superChapterName\":\"原创文章\",\"tags\":[],\"title\":\"带你了解腾讯开源的多渠道打包技术 VasDolly源码解析\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"xiangcman\",\"chapterId\":314,\"chapterName\":\"RV列表动效\",\"collect\":false,\"courseId\":13,\"desc\":\"快速利用RecyclerView的LayoutManager搭建流式布局  \",\"envelopePic\":\"http://www.wanandroid.com/blogimgs/36badc79-fb1e-460e-8368-6898c16ba723.png\",\"fresh\":false,\"id\":2829,\"link\":\"http://www.wanandroid.com/blog/show/2112\",\"niceDate\":\"2018-04-18\",\"origin\":\"\",\"projectLink\":\"https://github.com/xiangcman/LayoutManager-FlowLayout\",\"publishTime\":1524051620000,\"superChapterId\":294,\"superChapterName\":\"开源项目主Tab\",\"tags\":[{\"name\":\"项目\",\"url\":\"/project/list/1?cid=314\"}],\"title\":\"快速利用RecyclerView的LayoutManager搭建流式布局  \",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"醒着的码者\",\"chapterId\":245,\"chapterName\":\"集合相关\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2826,\"link\":\"https://www.jianshu.com/p/99ad883041d6\",\"niceDate\":\"2018-04-18\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1524051390000,\"superChapterId\":245,\"superChapterName\":\"Java深入\",\"tags\":[],\"title\":\"搞懂 HashSet & LinkedHashSet 源码 以及集合常见面试题目\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"Allen___\",\"chapterId\":334,\"chapterName\":\"Architecture Components\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2825,\"link\":\"https://www.jianshu.com/p/72c8efc3ad87\",\"niceDate\":\"2018-04-18\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1524050639000,\"superChapterId\":183,\"superChapterName\":\"5.+高新技术\",\"tags\":[],\"title\":\"快速掌握Room数据库框架（附Demo）\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"zejian_\",\"chapterId\":15,\"chapterName\":\"Service\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2824,\"link\":\"https://blog.csdn.net/javazejian/article/details/52709857\",\"niceDate\":\"2018-04-18\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1524040495000,\"superChapterId\":10,\"superChapterName\":\"四大组件\",\"tags\":[],\"title\":\"关于Android Service真正的完全详解，你需要知道的一切\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"小鄧子\",\"chapterId\":227,\"chapterName\":\"注解\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2820,\"link\":\"https://www.jianshu.com/p/28751130c038\",\"niceDate\":\"2018-04-16\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523887464000,\"superChapterId\":227,\"superChapterName\":\"注解 & 反射 & AOP\",\"tags\":[],\"title\":\"拦截控件点击 - 巧用ASM处理防抖\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"CHEN川\",\"chapterId\":346,\"chapterName\":\"JVM\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2819,\"link\":\"https://www.jianshu.com/p/f8d71e1e8821\",\"niceDate\":\"2018-04-16\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523887411000,\"superChapterId\":245,\"superChapterName\":\"Java深入\",\"tags\":[],\"title\":\"详解JVM内存管理与垃圾回收机制 (上)\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"张明云\",\"chapterId\":296,\"chapterName\":\"阅读\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2818,\"link\":\"https://www.jianshu.com/p/23c70f6df816\",\"niceDate\":\"2018-04-16\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523887364000,\"superChapterId\":202,\"superChapterName\":\"延伸技术\",\"tags\":[],\"title\":\"关于技术团队导师带新人的一些经验分享\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"红橙Darren\",\"chapterId\":249,\"chapterName\":\"干货资源\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2817,\"link\":\"https://www.jianshu.com/p/c0ec2a7fc26a\",\"niceDate\":\"2018-04-16\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523887339000,\"superChapterId\":249,\"superChapterName\":\"干货资源\",\"tags\":[],\"title\":\"Android进阶之旅与你同行\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"依然范特稀西 \",\"chapterId\":335,\"chapterName\":\"应用内更新\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2816,\"link\":\"https://www.jianshu.com/p/85913ed97af5\",\"niceDate\":\"2018-04-16\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523887288000,\"superChapterId\":135,\"superChapterName\":\"项目必备\",\"tags\":[],\"title\":\"Android app 在线更新那点事儿（适配Android6.0、7.0、8.0）\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"JokAr\",\"chapterId\":345,\"chapterName\":\"国际化\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2813,\"link\":\"https://juejin.im/post/5ac8d62c518825557e78a514\",\"niceDate\":\"2018-04-13\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523588234000,\"superChapterId\":135,\"superChapterName\":\"项目必备\",\"tags\":[],\"title\":\"Android国际化(多语言)实现，支持8.0\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"LawCoder\",\"chapterId\":134,\"chapterName\":\"SurfaceView\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2812,\"link\":\"https://blog.csdn.net/sdfsdfdfa/article/details/79862917\",\"niceDate\":\"2018-04-13\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523588009000,\"superChapterId\":126,\"superChapterName\":\"自定义控件\",\"tags\":[],\"title\":\"SurfaceView实战打造农药钻石夺宝\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"奇卓社\",\"chapterId\":269,\"chapterName\":\"官方发布\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2811,\"link\":\"http://mp.weixin.qq.com/s/4k3DBlxlSO2xNNKqjqUdaQ\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523533421000,\"superChapterId\":60,\"superChapterName\":\"开发环境\",\"tags\":[],\"title\":\"突破Android P(Preview 1)对调用隐藏API限制的方法\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"承香墨影\",\"chapterId\":67,\"chapterName\":\"网络基础\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2810,\"link\":\"https://www.jianshu.com/p/5008b707bd04\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523532290000,\"superChapterId\":98,\"superChapterName\":\"网络访问\",\"tags\":[],\"title\":\"一大波 Android 刘海屏来袭，全网最全适配技巧！\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"叶应是叶\",\"chapterId\":67,\"chapterName\":\"网络基础\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2809,\"link\":\"https://www.jianshu.com/p/6d2f324c8f42\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523532264000,\"superChapterId\":98,\"superChapterName\":\"网络访问\",\"tags\":[],\"title\":\"在 Android 设备上搭建 Web 服务器\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"水木飞雪\",\"chapterId\":67,\"chapterName\":\"网络基础\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2808,\"link\":\"https://www.jianshu.com/p/acbc7df5decd\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523532220000,\"superChapterId\":98,\"superChapterName\":\"网络访问\",\"tags\":[],\"title\":\"Android Protobuf应用及原理\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"筑梦师Winston\",\"chapterId\":296,\"chapterName\":\"阅读\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2807,\"link\":\"https://www.jianshu.com/p/9c112722fa77\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523532189000,\"superChapterId\":202,\"superChapterName\":\"延伸技术\",\"tags\":[],\"title\":\"全栈开发自学路线\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"meituan\",\"chapterId\":98,\"chapterName\":\"WebView\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2806,\"link\":\"https://tech.meituan.com/WebViewPerf.html\",\"niceDate\":\"2018-04-12\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523515464000,\"superChapterId\":98,\"superChapterName\":\"网络访问\",\"tags\":[],\"title\":\"WebView性能、体验分析与优化\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"那时青菜\",\"chapterId\":171,\"chapterName\":\"binder\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2805,\"link\":\"https://www.jianshu.com/p/63ad02ee266d\",\"niceDate\":\"2018-04-11\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523443955000,\"superChapterId\":153,\"superChapterName\":\"framework\",\"tags\":[],\"title\":\"Android 进程间的通信之AIDL\",\"type\":0,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"author\":\"红脸书生\",\"chapterId\":321,\"chapterName\":\"算法\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":2804,\"link\":\"http://www.cnblogs.com/steven_oyj/category/246990.html\",\"niceDate\":\"2018-04-11\",\"origin\":\"\",\"projectLink\":\"\",\"publishTime\":1523431053000,\"superChapterId\":245,\"superChapterName\":\"Java深入\",\"tags\":[],\"title\":\"五大常用算法\",\"type\":0,\"visible\":1,\"zan\":0}],\"offset\":20,\"over\":false,\"pageCount\":62,\"size\":20,\"total\":1238},\"errorCode\":0,\"errorMsg\":\"\"}";
        MainPagerListBean bean = gson.fromJson(s, MainPagerListBean.class);
        datas.addAll(bean.getData().getDatas());

        //banner数据
        String s1 = "{\"data\":[{\"desc\":\"最新项目上线啦~\",\"id\":13,\"imagePath\":\"http://www.wanandroid.com/blogimgs/5ae04af4-72b9-4696-81cb-1644cdcd2d29.jpg\",\"isVisible\":1,\"order\":0,\"title\":\"最新项目上线啦~\",\"type\":0,\"url\":\"http://www.wanandroid.com/pindex\"},{\"desc\":\"\",\"id\":6,\"imagePath\":\"http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png\",\"isVisible\":1,\"order\":1,\"title\":\"我们新增了一个常用导航Tab~\",\"type\":0,\"url\":\"http://www.wanandroid.com/navi\"},{\"desc\":\"一起来做个App吧\",\"id\":10,\"imagePath\":\"http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png\",\"isVisible\":1,\"order\":1,\"title\":\"一起来做个App吧\",\"type\":0,\"url\":\"http://www.wanandroid.com/blog/show/2\"},{\"desc\":\"\",\"id\":7,\"imagePath\":\"http://www.wanandroid.com/blogimgs/ffb61454-e0d2-46e7-bc9b-4f359061ae20.png\",\"isVisible\":1,\"order\":2,\"title\":\"送你一个暖心的Mock API工具\",\"type\":0,\"url\":\"http://www.wanandroid.com/blog/show/10\"},{\"desc\":\"\",\"id\":4,\"imagePath\":\"http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png\",\"isVisible\":1,\"order\":0,\"title\":\"看看别人的面经，搞定面试~\",\"type\":1,\"url\":\"http://www.wanandroid.com/article/list/0?cid=73\"},{\"desc\":\"\",\"id\":3,\"imagePath\":\"http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png\",\"isVisible\":1,\"order\":1,\"title\":\"兄弟，要不要挑个项目学习下?\",\"type\":1,\"url\":\"http://www.wanandroid.com/project\"},{\"desc\":\"加个友情链接吧~\",\"id\":11,\"imagePath\":\"http://www.wanandroid.com/blogimgs/84810df6-adf1-45bc-b3e2-294fa4e95005.png\",\"isVisible\":1,\"order\":1,\"title\":\"加个友情链接吧~\",\"type\":1,\"url\":\"http://www.wanandroid.com/ulink\"},{\"desc\":\"\",\"id\":2,\"imagePath\":\"http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png\",\"isVisible\":1,\"order\":2,\"title\":\"JSON工具\",\"type\":1,\"url\":\"http://www.wanandroid.com/tools/bejson\"},{\"desc\":\"\",\"id\":5,\"imagePath\":\"http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png\",\"isVisible\":1,\"order\":3,\"title\":\"微信文章合集\",\"type\":1,\"url\":\"http://www.wanandroid.com/blog/show/6\"}],\"errorCode\":0,\"errorMsg\":\"\"}";
        MainPagerBannerBean bannerBean = gson.fromJson(s1, MainPagerBannerBean.class);

        adapter = new MainPagerAdapter(datas, bannerBean.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.destroy();
    }

    @Inject
    public MainPagerFragment() {

    }
}
