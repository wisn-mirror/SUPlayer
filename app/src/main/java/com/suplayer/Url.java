package com.suplayer;

import com.suplayer.douyin.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wisn on 2019-07-24 12:10.
 */
public class Url {
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
}
