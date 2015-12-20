/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.java.sip.communicator.service.hid;

/**
 * Human Interface Device service.
 * This service is used to regenerates key and mouse events on the local
 * computer. It is typically used in case of remote control features.
 *
 * @author Sebastien Vincent
 */
public interface HIDService
{
    /**
     * Press a specific key using its keycode.
     *
     * @param keycode the Java keycode, all available keycode can be found
     * in java.awt.event.KeyEvent class (VK_A, VK_SPACE, ...)
     * @see java.awt.event.KeyEvent
     * @see java.awt.Robot#keyRelease(int keycode)
     */
    public void keyPress(int keycode);

    /**
     * Release a specific key using its keycode.
     *
     * @param keycode the Java keycode, all available keycode can be found
     * in java.awt.event.KeyEvent class (VK_A, VK_SPACE, ...)
     * @see java.awt.event.KeyEvent
     * @see java.awt.Robot#keyRelease(int keycode)
     */
    public void keyRelease(int keycode);

    /**
     * Press a specific key using its char representation.
     *
     * @param key char representation of the key
     */
    public void keyPress(char key);

    /**
     * Release a specific key using its char representation.
     *
     * @param key char representation of the key
     */
    public void keyRelease(char key);

    /**
     * Press a mouse button(s).
     *
     * @param btns button masks
     * @see java.awt.Robot#mousePress(int btns)
     */
    public void mousePress(int btns);

    /**
     * Release a mouse button(s).
     *
     * @param btns button masks
     * @see java.awt.Robot#mouseRelease(int btns)
     */
    public void mouseRelease(int btns);

    /**
     * Move the mouse on the screen.
     *
     * @param x x position on the screen
     * @param y y position on the screen
     * @see java.awt.Robot#mouseMove(int x, int y)
     */
    public void mouseMove(int x, int y);

    /**
     * Release a mouse button(s).
     *
     * @param rotation wheel rotation (could be negative or positive depending
     * on the direction).
     * @see java.awt.Robot#mouseWheel(int wheelAmt)
     */
    public void mouseWheel(int rotation);
}
