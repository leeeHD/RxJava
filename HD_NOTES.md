### extends Observable ###

    static final class FailingObservable extends Observable<Object> {

        protected FailingObservable() {
            super(new OnSubscribe<Object>() {
                @Override
                public void call(Subscriber<? super Object> t) {
                    throw new TestException("Forced failure");
                }
            });
        }
        
    }