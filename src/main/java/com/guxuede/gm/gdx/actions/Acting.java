package com.guxuede.gm.gdx.actions;

import com.badlogic.gdx.utils.Pool;

abstract public class Acting extends Action {
    protected float time;
    protected boolean began, complete;

    public Acting() {
    }

    public boolean act (float delta) {
        if (complete) return true;
        Pool pool = getPool();
        setPool(null); // Ensure this action can't be returned to the pool while executing.
        try {
            if (!began) {
                begin();
                began = true;
            }
            time += delta;
            complete = update(delta);
            if (complete) end();
            return complete;
        } finally {
            setPool(pool);
        }
    }

    /** Called the first time {@link #act(float)} is called. This is a good place to query the {@link #actor actor's} starting
     * state. */
    protected void begin () {
    }

    /** Called the last time {@link #act(float)} is called. */
    protected void end () {
    }

    abstract protected boolean update (float delta);

    public void reset () {
        super.reset();
        began = false;
        complete = false;
        time = 0;
    }

    @Override
    public void restart() {
        super.restart();
        began = false;
        complete = false;
        time = 0;
    }

    public boolean isComplete() {
        return complete;
    }

}
