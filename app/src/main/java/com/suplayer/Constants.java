package com.suplayer;

import com.suplayer.bean.AliveBean;
import com.suplayer.example.douyin.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-24 12:10.
 */
public class Constants {
    public static String data="data";
    public static String name="name";


    public static final String VOD_URL = "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4";

    public static final String ip = "http://192.168.132.35:8080/";
//    public static final String ip = "http://172.19.54.203:8080/";

    public static final String[] local_resvideo = {
            /* ip + "media/video/newhome.mp4",
             ip + "media/video/video_aaa.mp4",
             ip + "media/video/video_ddd.mkv",
             ip + "media/video/video_ccc.mp4",
             ip + "media/video/video_ddd.mkv"*/
            VOD_URL,
            VOD_URL,
            VOD_URL,
            VOD_URL,
            VOD_URL
    };

    public static List<VideoBean> getVideoBean() {
        ArrayList<VideoBean> videoBeans = new ArrayList<>();
        for (int i = 0; i < local_resvideo.length; i++) {
            VideoBean videoBean = new VideoBean("标题" + i, res[i], local_resvideo[i]);
            videoBeans.add(videoBean);
        }
        return videoBeans;
    }

    public static final String[] res = {
            "http://img.hb.aicdn.com/3e1247e78d3f3fbe744acf4908be5ee36212fd1d1852b-DHtPYL_sq320",
            "http://img.hb.aicdn.com/5ca18a36a5c2fb3acc1e63b6a3df1287643b7aec11849-xWzSg2_sq320",
            "http://img.hb.aicdn.com/89db933ca820114e4dcb110e691fa6b14751bdb0ce477-unZJrW_fw658",
            "http://img.hb.aicdn.com/861f030ccfac996128a22b531877567cef31542e724ec-49Up19_fw658",
            "http://img.hb.aicdn.com/10e73e8978b027fefcac06a22457641f251b36871bfd8-IDv6E1_fw658",
            "http://img.hb.aicdn.com/a66e806c0e0cb6df0abe704c8dc7674246fbd4c58c02b-QkzyiN_fw658",
            "http://img.hb.aicdn.com/2b377823932cf89d7d06c2e32528add3221f66c3247bd-I5idwv_fw658",
            "http://img.hb.aicdn.com/fd9eea74af8e3a2281ed89deb967e3c0d62bb0e2f7d3c-GSZCSI_fw658",
            "http://img.hb.aicdn.com/d7ecb51f3a48d84e8f6e4192ef198a1a69b20875fc17f-7ZjctS_fw658",
            "http://img.hb.aicdn.com/80006ed344ed8dee7ad8142b3c4dc1b51cbf207c3097a-SGiu5P_fw658",
            "http://img.hb.aicdn.com/ca0db256466b269ed935dd960e6ff057645de6c9a72fe-Pm6RsK_fw658",
            "http://img.hb.aicdn.com/9755052fc4686ff04224c6c97a351357299e51b255b06-l12fbz_fw658",
            "http://img.hb.aicdn.com/61a7983982802dd519401cd63eb586d91a0f5548272b8-Klo5uo_fw658",
            "http://img.hb.aicdn.com/f767ac4cdd2a0a0a2041c512147c39ef5784969b21feb2-v7Gv7t_fw658",
            "http://img.hb.aicdn.com/11ea421e5f873b0c05a09a5dd482cb28b54a0c94166948-yRZJcI_fw658",
            "http://img.hb.aicdn.com/22e72462263c3678d8286bcc6517e2aa1a56928f8689e-c1SFtw_fw658",
            "http://img.hb.aicdn.com/55ce9b4343198f692d9273337d10043a392475bb6052a-t0qCzu_fw658",
            "http://img.hb.aicdn.com/fb2de9e00f54f009d71158584b06f2a9e76b50f41179e-OHSnJo_fw658",
            "http://img.hb.aicdn.com/7f3143be6a221177bedf078617e0ea6812b6e17af6cc-0UJxnU_fw658",
            "http://img.hb.aicdn.com/d74018375aa00dfe1c8895536c689d9182a012ae20cf4-DBy9Om_fw658",
            "http://img.hb.aicdn.com/49fdefbb6a6d1575ed55230e158371f59f5c4445323ffd-hxLANR_fw658",
            "http://img.hb.aicdn.com/c464f3524dfebc215f0049afea542f9aa590c10713743-hiizpP_fw658",
            "http://img.hb.aicdn.com/b939f97e63d3a9a17a042f510fcd9152d103e6fc17ac2-luRKPF_fw658",
            "http://img.hb.aicdn.com/98163fd8aec27658f847f3337b77859dd1c6f66a26d0d-XdWfJG_fw658",
            "http://img.hb.aicdn.com/4ccf7a5bffa66ec4efaa9f5207b5d59a2d5a33bd1a881-G9ClQz_fw658",
            "http://img.hb.aicdn.com/00050df3f0afa491f176c8f7531ef69b98a582021dced-Qp8zzT_fw658",
            "http://img.hb.aicdn.com/bd369f7734738be46cd41f5a6d25b5040bd0535930463-rVtz8k_fw658",
            "http://img.hb.aicdn.com/7253e9679ed381f9b686068e8d32bf5a759d1937168e1-0EbxUB_fw658",
            "http://img.hb.aicdn.com/653e5eafbd3a231ae5d521dc7b30334ede845ea63a598-cFoxuf_fw658",
            "http://img.hb.aicdn.com/f37493c57c1749b82e07398890af8298557c9b6eba81-fInqK7_fw658",
            "http://img.hb.aicdn.com/210a7bcf847da25a7fc60f4f87fc50b4d3c8c34a179b4-n4furO_fw658",
            "http://img.hb.aicdn.com/0e147c605d2e70f0ebd86ea1c341f7d13c7de734467bb-III9AK_fw658",
            "http://img.hb.aicdn.com/03cd223ca89efb468b8847f3f8bbb17072c8a448461f6-ZdJphL_fw658",
            "http://img.hb.aicdn.com/2f09c45c2545a189f6df377a587c1f8bb6c8a48ce858-Yw8qYf_fw658",
            "http://img.hb.aicdn.com/380533e59fed767ee4f658e14eb0c1290812de501a769-Ro8nCh_sq320",
            "http://img.hb.aicdn.com/a933d0724f48c27beba62663be948e28bbf1cedac032f-1JI94w_fw658",
            "http://img.hb.aicdn.com/2dacc4645b7b8abd4e2dc849510efd05d531ad329c78-BYQdu1_sq320",
            "http://img.hb.aicdn.com/0b1128cfa4e53b0b4a347d2a834af081e3c90bad196ac-hvG7nm_sq320",
            "http://img.hb.aicdn.com/b237b43b475642df0f4bd9a8e6df10002da42c3316530-7hx6ho_sq320",
            "http://img.hb.aicdn.com/aac4e8146a6b14f48f6983c9afe4aa7e0e5fa79811f1a-6bJ8FY_sq320",
            "http://img.hb.aicdn.com/52abfb758d9c4f88fc648414037665c837f586cc1a62a-uW5yjm_sq320",
            "http://img.hb.aicdn.com/6775e359b4f49e28c00143db622e5e045bb08ce61390a-1wO40p_sq320",
            "http://img.hb.aicdn.com/3e1247e78d3f3fbe744acf4908be5ee36212fd1d1852b-DHtPYL_sq320",
            "http://img.hb.aicdn.com/5ca18a36a5c2fb3acc1e63b6a3df1287643b7aec11849-xWzSg2_sq320",
            "http://img.hb.aicdn.com/89db933ca820114e4dcb110e691fa6b14751bdb0ce477-unZJrW_fw658",
            "http://img.hb.aicdn.com/861f030ccfac996128a22b531877567cef31542e724ec-49Up19_fw658",
            "http://img.hb.aicdn.com/10e73e8978b027fefcac06a22457641f251b36871bfd8-IDv6E1_fw658",
            "http://img.hb.aicdn.com/a66e806c0e0cb6df0abe704c8dc7674246fbd4c58c02b-QkzyiN_fw658",
            "http://img.hb.aicdn.com/2b377823932cf89d7d06c2e32528add3221f66c3247bd-I5idwv_fw658",
            "http://img.hb.aicdn.com/fd9eea74af8e3a2281ed89deb967e3c0d62bb0e2f7d3c-GSZCSI_fw658",
            "http://img.hb.aicdn.com/d7ecb51f3a48d84e8f6e4192ef198a1a69b20875fc17f-7ZjctS_fw658",
            "http://img.hb.aicdn.com/80006ed344ed8dee7ad8142b3c4dc1b51cbf207c3097a-SGiu5P_fw658",
            "http://img.hb.aicdn.com/ca0db256466b269ed935dd960e6ff057645de6c9a72fe-Pm6RsK_fw658",
            "http://img.hb.aicdn.com/9755052fc4686ff04224c6c97a351357299e51b255b06-l12fbz_fw658",
            "http://img.hb.aicdn.com/61a7983982802dd519401cd63eb586d91a0f5548272b8-Klo5uo_fw658",
            "http://img.hb.aicdn.com/f767ac4cdd2a0a0a2041c512147c39ef5784969b21feb2-v7Gv7t_fw658",
            "http://img.hb.aicdn.com/11ea421e5f873b0c05a09a5dd482cb28b54a0c94166948-yRZJcI_fw658",
            "http://img.hb.aicdn.com/22e72462263c3678d8286bcc6517e2aa1a56928f8689e-c1SFtw_fw658",
            "http://img.hb.aicdn.com/55ce9b4343198f692d9273337d10043a392475bb6052a-t0qCzu_fw658",
            "http://img.hb.aicdn.com/fb2de9e00f54f009d71158584b06f2a9e76b50f41179e-OHSnJo_fw658",
            "http://img.hb.aicdn.com/7f3143be6a221177bedf078617e0ea6812b6e17af6cc-0UJxnU_fw658",
            "http://img.hb.aicdn.com/d74018375aa00dfe1c8895536c689d9182a012ae20cf4-DBy9Om_fw658",
            "http://img.hb.aicdn.com/49fdefbb6a6d1575ed55230e158371f59f5c4445323ffd-hxLANR_fw658",
            "http://img.hb.aicdn.com/c464f3524dfebc215f0049afea542f9aa590c10713743-hiizpP_fw658",
            "http://img.hb.aicdn.com/b939f97e63d3a9a17a042f510fcd9152d103e6fc17ac2-luRKPF_fw658",
            "http://img.hb.aicdn.com/98163fd8aec27658f847f3337b77859dd1c6f66a26d0d-XdWfJG_fw658",
            "http://img.hb.aicdn.com/4ccf7a5bffa66ec4efaa9f5207b5d59a2d5a33bd1a881-G9ClQz_fw658",
            "http://img.hb.aicdn.com/00050df3f0afa491f176c8f7531ef69b98a582021dced-Qp8zzT_fw658",
            "http://img.hb.aicdn.com/bd369f7734738be46cd41f5a6d25b5040bd0535930463-rVtz8k_fw658",
            "http://img.hb.aicdn.com/7253e9679ed381f9b686068e8d32bf5a759d1937168e1-0EbxUB_fw658",
            "http://img.hb.aicdn.com/653e5eafbd3a231ae5d521dc7b30334ede845ea63a598-cFoxuf_fw658",
            "http://img.hb.aicdn.com/f37493c57c1749b82e07398890af8298557c9b6eba81-fInqK7_fw658",
            "http://img.hb.aicdn.com/210a7bcf847da25a7fc60f4f87fc50b4d3c8c34a179b4-n4furO_fw658",
            "http://img.hb.aicdn.com/0e147c605d2e70f0ebd86ea1c341f7d13c7de734467bb-III9AK_fw658",
            "http://img.hb.aicdn.com/03cd223ca89efb468b8847f3f8bbb17072c8a448461f6-ZdJphL_fw658",
            "http://img.hb.aicdn.com/2f09c45c2545a189f6df377a587c1f8bb6c8a48ce858-Yw8qYf_fw658",
            "http://img.hb.aicdn.com/380533e59fed767ee4f658e14eb0c1290812de501a769-Ro8nCh_sq320",
            "http://img.hb.aicdn.com/a933d0724f48c27beba62663be948e28bbf1cedac032f-1JI94w_fw658",
            "http://img.hb.aicdn.com/2dacc4645b7b8abd4e2dc849510efd05d531ad329c78-BYQdu1_sq320",
            "http://img.hb.aicdn.com/0b1128cfa4e53b0b4a347d2a834af081e3c90bad196ac-hvG7nm_sq320",
            "http://img.hb.aicdn.com/b237b43b475642df0f4bd9a8e6df10002da42c3316530-7hx6ho_sq320",
            "http://img.hb.aicdn.com/aac4e8146a6b14f48f6983c9afe4aa7e0e5fa79811f1a-6bJ8FY_sq320",
            "http://img.hb.aicdn.com/52abfb758d9c4f88fc648414037665c837f586cc1a62a-uW5yjm_sq320",
            "http://img.hb.aicdn.com/6775e359b4f49e28c00143db622e5e045bb08ce61390a-1wO40p_sq320",
            "http://img.hb.aicdn.com/3e1247e78d3f3fbe744acf4908be5ee36212fd1d1852b-DHtPYL_sq320",
            "http://img.hb.aicdn.com/5ca18a36a5c2fb3acc1e63b6a3df1287643b7aec11849-xWzSg2_sq320",
            "http://img.hb.aicdn.com/89db933ca820114e4dcb110e691fa6b14751bdb0ce477-unZJrW_fw658",
            "http://img.hb.aicdn.com/861f030ccfac996128a22b531877567cef31542e724ec-49Up19_fw658",
            "http://img.hb.aicdn.com/10e73e8978b027fefcac06a22457641f251b36871bfd8-IDv6E1_fw658",
            "http://img.hb.aicdn.com/a66e806c0e0cb6df0abe704c8dc7674246fbd4c58c02b-QkzyiN_fw658",
            "http://img.hb.aicdn.com/2b377823932cf89d7d06c2e32528add3221f66c3247bd-I5idwv_fw658",
            "http://img.hb.aicdn.com/fd9eea74af8e3a2281ed89deb967e3c0d62bb0e2f7d3c-GSZCSI_fw658",
            "http://img.hb.aicdn.com/d7ecb51f3a48d84e8f6e4192ef198a1a69b20875fc17f-7ZjctS_fw658",
            "http://img.hb.aicdn.com/80006ed344ed8dee7ad8142b3c4dc1b51cbf207c3097a-SGiu5P_fw658",
            "http://img.hb.aicdn.com/ca0db256466b269ed935dd960e6ff057645de6c9a72fe-Pm6RsK_fw658",
            "http://img.hb.aicdn.com/9755052fc4686ff04224c6c97a351357299e51b255b06-l12fbz_fw658",
            "http://img.hb.aicdn.com/61a7983982802dd519401cd63eb586d91a0f5548272b8-Klo5uo_fw658",
            "http://img.hb.aicdn.com/f767ac4cdd2a0a0a2041c512147c39ef5784969b21feb2-v7Gv7t_fw658",
            "http://img.hb.aicdn.com/11ea421e5f873b0c05a09a5dd482cb28b54a0c94166948-yRZJcI_fw658",
            "http://img.hb.aicdn.com/22e72462263c3678d8286bcc6517e2aa1a56928f8689e-c1SFtw_fw658",
            "http://img.hb.aicdn.com/55ce9b4343198f692d9273337d10043a392475bb6052a-t0qCzu_fw658",
            "http://img.hb.aicdn.com/fb2de9e00f54f009d71158584b06f2a9e76b50f41179e-OHSnJo_fw658",
            "http://img.hb.aicdn.com/7f3143be6a221177bedf078617e0ea6812b6e17af6cc-0UJxnU_fw658",
            "http://img.hb.aicdn.com/d74018375aa00dfe1c8895536c689d9182a012ae20cf4-DBy9Om_fw658",
            "http://img.hb.aicdn.com/49fdefbb6a6d1575ed55230e158371f59f5c4445323ffd-hxLANR_fw658",
            "http://img.hb.aicdn.com/c464f3524dfebc215f0049afea542f9aa590c10713743-hiizpP_fw658",
            "http://img.hb.aicdn.com/b939f97e63d3a9a17a042f510fcd9152d103e6fc17ac2-luRKPF_fw658",
            "http://img.hb.aicdn.com/98163fd8aec27658f847f3337b77859dd1c6f66a26d0d-XdWfJG_fw658",
            "http://img.hb.aicdn.com/4ccf7a5bffa66ec4efaa9f5207b5d59a2d5a33bd1a881-G9ClQz_fw658",
            "http://img.hb.aicdn.com/00050df3f0afa491f176c8f7531ef69b98a582021dced-Qp8zzT_fw658",
            "http://img.hb.aicdn.com/bd369f7734738be46cd41f5a6d25b5040bd0535930463-rVtz8k_fw658",
            "http://img.hb.aicdn.com/7253e9679ed381f9b686068e8d32bf5a759d1937168e1-0EbxUB_fw658",
            "http://img.hb.aicdn.com/653e5eafbd3a231ae5d521dc7b30334ede845ea63a598-cFoxuf_fw658",
            "http://img.hb.aicdn.com/f37493c57c1749b82e07398890af8298557c9b6eba81-fInqK7_fw658",
            "http://img.hb.aicdn.com/210a7bcf847da25a7fc60f4f87fc50b4d3c8c34a179b4-n4furO_fw658",
            "http://img.hb.aicdn.com/0e147c605d2e70f0ebd86ea1c341f7d13c7de734467bb-III9AK_fw658",
            "http://img.hb.aicdn.com/03cd223ca89efb468b8847f3f8bbb17072c8a448461f6-ZdJphL_fw658",
            "http://img.hb.aicdn.com/2f09c45c2545a189f6df377a587c1f8bb6c8a48ce858-Yw8qYf_fw658",
            "http://img.hb.aicdn.com/380533e59fed767ee4f658e14eb0c1290812de501a769-Ro8nCh_sq320",
            "http://img.hb.aicdn.com/a933d0724f48c27beba62663be948e28bbf1cedac032f-1JI94w_fw658",
            "http://img.hb.aicdn.com/2dacc4645b7b8abd4e2dc849510efd05d531ad329c78-BYQdu1_sq320",
            "http://img.hb.aicdn.com/0b1128cfa4e53b0b4a347d2a834af081e3c90bad196ac-hvG7nm_sq320",
            "http://img.hb.aicdn.com/b237b43b475642df0f4bd9a8e6df10002da42c3316530-7hx6ho_sq320",
            "http://img.hb.aicdn.com/aac4e8146a6b14f48f6983c9afe4aa7e0e5fa79811f1a-6bJ8FY_sq320",
            "http://img.hb.aicdn.com/52abfb758d9c4f88fc648414037665c837f586cc1a62a-uW5yjm_sq320",
            "http://img.hb.aicdn.com/6775e359b4f49e28c00143db622e5e045bb08ce61390a-1wO40p_sq320",
    };

    /**
     * 测试直播app
     */

    public static final String[][] aliveData = {
            {"CCTV-1综合", "http://ivi.bupt.edu.cn/hls/cctv1.m3u8"},
            {"CCTV-2财经", "http://ivi.bupt.edu.cn/hls/cctv2.m3u8"},
            {"CCTV-3综艺", "http://ivi.bupt.edu.cn/hls/cctv3.m3u8"},
            {"CCTV-4中文国际 ", "http://ivi.bupt.edu.cn/hls/cctv4.m3u8"},
            {"CCTV-6", "http://ivi.bupt.edu.cn/hls/cctv6.m3u8"},
            {"CCTV-7军事农业 ", "http://ivi.bupt.edu.cn/hls/cctv7.m3u8"},
            {"CCTV-8电视剧", "http://ivi.bupt.edu.cn/hls/cctv8.m3u8"},
            {"CCTV-9纪录", "http://ivi.bupt.edu.cn/hls/cctv9.m3u8"},
            {"CCTV-10科教", "http://ivi.bupt.edu.cn/hls/cctv10.m3u8"},
            {"CCTV-11戏曲", "http://ivi.bupt.edu.cn/hls/cctv11.m3u8"},
            {"CCTV-12社会与法", "http://ivi.bupt.edu.cn/hls/cctv12.m3u8"},
            {"CCTV-13新闻", "http://ivi.bupt.edu.cn/hls/cctv13.m3u8"},
            {"CCTV-14少儿", "http://ivi.bupt.edu.cn/hls/cctv14.m3u8"},
            {"CCTV-15音乐", "http://ivi.bupt.edu.cn/hls/cctv15.m3u8"},
            {"CCTV-NEWS", "http://ivi.bupt.edu.cn/hls/cctv16.m3u8"},
            {"北京卫视", "http://ivi.bupt.edu.cn/hls/btv1.m3u8"},
            {"北京文艺", "http://ivi.bupt.edu.cn/hls/btv2.m3u8"},
            {"北京科教", "http://ivi.bupt.edu.cn/hls/btv3.m3u8"},
            {"北京影视", "http://ivi.bupt.edu.cn/hls/btv4.m3u8"},
            {"北京财经", "http://ivi.bupt.edu.cn/hls/btv5.m3u8"},
            {"北京生活", "http://ivi.bupt.edu.cn/hls/btv7.m3u8"},
            {"北京青年", "http://ivi.bupt.edu.cn/hls/btv8.m3u8"},
            {"北京新闻", "http://ivi.bupt.edu.cn/hls/btv9.m3u8"},
            {"北京卡酷少儿", "http://ivi.bupt.edu.cn/hls/btv10.m3u8"},
            {"深圳卫视", "http://ivi.bupt.edu.cn/hls/sztv.m3u8"},
            {"安徽卫视", "http://ivi.bupt.edu.cn/hls/ahtv.m3u8"},
            {"河南卫视", "http://ivi.bupt.edu.cn/hls/hntv.m3u8"},
            {"陕西卫视", "http://ivi.bupt.edu.cn/hls/sxtv.m3u8"},
            {"吉林卫视", "http://ivi.bupt.edu.cn/hls/jltv.m3u8"},
            {"广东卫视", "http://ivi.bupt.edu.cn/hls/gdtv.m3u8"},
            {"山东卫视", "http://ivi.bupt.edu.cn/hls/sdtv.m3u8"},
            {"湖北卫视", "http://ivi.bupt.edu.cn/hls/hbtv.m3u8"},
            {"广西卫视", "http://ivi.bupt.edu.cn/hls/gxtv.m3u8"},
            {"河北卫视", "http://ivi.bupt.edu.cn/hls/hebtv.m3u8"},
            {"西藏卫视", "http://ivi.bupt.edu.cn/hls/xztv.m3u8"},
            {"内蒙古卫视", "http://ivi.bupt.edu.cn/hls/nmtv.m3u8"},
            {"青海卫视", "http://ivi.bupt.edu.cn/hls/qhtv.m3u8"},
            {"四川卫视", "http://ivi.bupt.edu.cn/hls/sctv.m3u8"},
            {"江苏卫视", "http://ivi.bupt.edu.cn/hls/jstv.m3u8"},
            {"天津卫视", "http://ivi.bupt.edu.cn/hls/tjtv.m3u8"},
            {"山西卫视", "http://ivi.bupt.edu.cn/hls/sxrtv.m3u8"},
            {"辽宁卫视", "http://ivi.bupt.edu.cn/hls/lntv.m3u8"},
            {"厦门卫视", "http://ivi.bupt.edu.cn/hls/xmtv.m3u8"},
            {"新疆卫视", "http://ivi.bupt.edu.cn/hls/xjtv.m3u8"},
            {"东方卫视", "http://ivi.bupt.edu.cn/hls/dftv.m3u8"},
            {"黑龙江卫视", "http://ivi.bupt.edu.cn/hls/hljtv.m3u8"},
            {"湖南卫视", "http://ivi.bupt.edu.cn/hls/hunantv.m3u8"},
            {"云南卫视", "http://ivi.bupt.edu.cn/hls/yntv.m3u8"},
            {"江西卫视", "http://ivi.bupt.edu.cn/hls/jxtv.m3u8"},
            {"福建东南卫视", "http://ivi.bupt.edu.cn/hls/dntv.m3u8"},
            {"浙江卫视", "http://ivi.bupt.edu.cn/hls/zjtv.m3u8"},
            {"贵州卫视", "http://ivi.bupt.edu.cn/hls/gztv.m3u8"},
            {"宁夏卫视", "http://ivi.bupt.edu.cn/hls/nxtv.m3u8"},
            {"甘肃卫视", "http://ivi.bupt.edu.cn/hls/gstv.m3u8"},
            {"重庆卫视", "http://ivi.bupt.edu.cn/hls/cqtv.m3u8"},
            {"兵团卫视", "http://ivi.bupt.edu.cn/hls/bttv.m3u8"},
            {"旅游卫视", "http://ivi.bupt.edu.cn/hls/lytv.m3u8"}};

    public static List<AliveBean> getTestBean() {
        List<AliveBean> resut = new ArrayList<>();
        for (String[] data : aliveData) {
            resut.add(new AliveBean(data[0], data[1]));
        }
        return resut;
    }
}


