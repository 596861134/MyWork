package com.interval;

import io.reactivex.disposables.Disposable;

/**
 * //
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                    /  _||||| -:- |||||-  \
 * //                    |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . ___
 * //               ."" '<  `.___\_<|>_/___.'  >' "".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                      佛祖保佑       永无BUG
 * Created by czf on 2017/12/11.
 */

public abstract class PollingRequest {

    //每个Subject的唯一标识
    protected String tag;

    //事件接口
    protected EventInterface eventInterface;

    //轮询动作
    protected PollingAction pollingAction;

    public PollingRequest(String tag, EventInterface eventInterface, PollingAction pollingAction) {
        this.tag = tag;
        this.eventInterface = eventInterface;
        this.pollingAction = pollingAction;
    }

    public abstract Disposable execute(PollingManager pollingManager);

    public String getTag() {
        return tag;
    }

    public EventInterface getEventInterface() {
        return eventInterface;
    }

    public interface PollingAction {
        void doAction(Object accept);
    }
}
