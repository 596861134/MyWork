package com.interval;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

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

public class SubjectModel<T> {
    //Subject
    private BehaviorSubject<T> behaviorSubject;

    //订阅关系
    private Disposable disposable;

    //轮询器
    private PollingRequest pollingRequest;

    public void clearSubject(){
        if (null == disposable || disposable.isDisposed()) return;

        disposable.dispose();
    }
}
