package org.chzz.map.engine;


//作者:copy 邮件:2499551993@qq.com
//创建时间:15/9/17 下午2:01
//描述:

import org.chzz.map.base.utils.ConstantValues;
import org.chzz.map.model.bean.CoordinatesEntity;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface Engine {
    String comeFrom = "Android";

    //--------------------------------用户相关接口-------------------------------------------------

    /**
     * 、坐标转换service
     *
     * @param params
     * @return
     */
    @GET(ConstantValues.a)
    Observable<CoordinatesEntity> getCoordinates(@QueryMap Map<String, Object> params);
}