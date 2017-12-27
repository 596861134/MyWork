package com.interval;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
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

public class PollingManager {
    private HashMap<String, SubjectModel<EventInterface>> activeSubjectMap;

    private static PollingManager manager;


    private PollingManager() {
        activeSubjectMap = new HashMap<>();
    }

    public static PollingManager getInstance() {
        if (null == manager) {
            synchronized (PollingManager.class) {
                if (null == manager) {
                    manager = new PollingManager();
                }
            }
        }

        return manager;
    }

    public ObservableTransformer<Long, Long> composeEvent(final String tag, final EventInterface outEvent) {

//        BehaviorSubject<EventInterface> subject = getSubject(tag);
//        if (null == subject) {
//            Log.d("TAG","subject = null");
//            return new EmptyObservableTransformer();
//        }
//
//        final Observable observable = subject.filter(new Predicate<EventInterface>() {
//            @Override
//            public boolean test(EventInterface event) throws Exception {
//                Log.d("TAG","receive event: " + event);
//                boolean filter = outEvent == event || event == Event.BizEvent.ALL;
//                if (filter) clearSubject(tag);
//                return filter;
//            }
//        });
//
//        return new ObservableTransformer<Long, Long>() {
//            @Override
//            public ObservableSource<Long> apply(Observable<Long> upstream) {
//                return upstream.subscribeOn(Schedulers.io()).takeUntil(observable);
//            }
//        };

        return null;
    }

    public BehaviorSubject<EventInterface> bindIntervalEvent(int interval, @NonNull String tag, @NonNull EventInterface eventInterface, PollingRequest.PollingAction action){

//        //1.创建轮询器
//        IntervalPolling intervalPolling = new IntervalPolling(interval, tag, eventInterface, action);
//
//        //2.创建Subject
//        createSubject(intervalPolling);
//
//        //3.启动轮询
//        startPolling(tag);
//
//        //4.返回Subject
//        return activeSubjectMap.get(tag).getBehaviorSubject();

        return null;
    }

    public BehaviorSubject<EventInterface> bindLifeCycle(@NonNull String tag, @NonNull EventInterface eventInterface){
//        //1.创建轮询器
//        PollingRequest request = new LifePolling(tag, eventInterface, null);
//
//        //2.创建Subject
//        createSubject(request);
//
//        //3.启动轮询
//        startPolling(tag);
//
//        //4.返回Subject
//        return activeSubjectMap.get(tag).getBehaviorSubject();
        return null;
    }

    public boolean stopPolling(String tag, EventInterface event) {
//        BehaviorSubject<EventInterface> subject = getSubject(tag);
//        if (null == subject) {
//            Log.e("TAG","can not find subject according to the "+ tag);
//            return false;
//        }
//
//        subject.onNext(event);
//        Log.i("TAG","Stop Polling SubjectTag =  " + tag + ", Event = " + event.toString());
        return true;
    }

    public void emitEvent( @NonNull EventInterface event){
//        if (null == activeSubjectMap) return;
//
//        for (Map.Entry<String, SubjectModel<EventInterface>> next : activeSubjectMap.entrySet()) {
//            BehaviorSubject<EventInterface> behaviorSubject = next.getValue().getBehaviorSubject();
//            if (null == behaviorSubject) return;
//            behaviorSubject.onNext(event);
//        }
    }
}
