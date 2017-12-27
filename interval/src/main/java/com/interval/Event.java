package com.interval;

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

public interface Event {

    enum FragmentEvent implements EventInterface{
        ATTACH,
        CREATE,
        CREATE_VIEW,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY_VIEW,
        DESTROY,
        DETACH
    }

    enum ActivityEvent implements EventInterface{
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    enum BizEvent implements EventInterface{
        INTERVAL,
        TIMER,
        STOP,
        ALL
    }
}
