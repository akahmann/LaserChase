package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    /**
     * Constructs a GameState Manager that contains a stack of states. States represent what state
     * the game is currently in. For example there could be a menu state, a play state, or a
     * gameover state. It is a stack because once a state is over it will go back to the previous
     * state.
     */
    public GameStateManager(){
        states = new Stack<State>();
    }

    /**
     * Adds a new state to the stack. The front of stack represents the current state displayed
     * and active.
     *
     * @param state A new state to be displayed and active.
     */
    public void push(State state){
        states.push(state);
    }

    /**
     * Exit out of the current state.
     */
    public void pop(){
        states.pop().dispose();
    }


    public void set(State state){
        states.pop(); // might need .dispose() later
        states.push(state);

    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);

    }


}
