package org.chzz.map.base;

import org.chzz.map.App;
import org.chzz.map.engine.Engine;

import java.util.Map;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--16:56
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public interface BaseEntity {


    public class BaseBean<T>   {

        protected Engine mEngine = App.getInstance().getEngine();

        private int code;

        private String message;

        public void setCode(int code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, String> param;
    }

    public abstract class ListBean<T> extends BaseBean<T> implements IListBean {
        @Override
        public void setParam(Map<String, String> param) {
            this.param = param;
        }
    }

    public interface IListBean {
        //  public Observable getPageAt(Map<String, Object> map);

        void setParam(Map<String, String> param);
    }
}
