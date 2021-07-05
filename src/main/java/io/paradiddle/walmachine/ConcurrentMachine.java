/*
 * paradiddle-wal-machine A generic state machine library that utilizes a write-ahead log for consistency.
 * Copyright (c) 2021 Michael Juliano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to:
 *
 *     Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor,
 *     Boston, MA 02110-1301 USA.
 */
package io.paradiddle.walmachine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConcurrentMachine<T extends Instruction> implements Machine<T>, AutoCloseable {
    private final BlockingQueue<T> instructions;
    private final Thread background;

    /**
     * Wraps the given {@link Machine} making it Thread-safe.
     * @param machine The {@link Machine} to make Thread-safe
     */
    public ConcurrentMachine(final Machine<T> machine) {
        this.instructions = new LinkedBlockingQueue<>();
        this.background = new Thread(() -> {
            try {
                while(!Thread.currentThread().isInterrupted()) {
                    final var instruction = this.instructions.take();
                    machine.instruct(instruction);
                }
            } catch (final InterruptedException e) {
                // Interrupted; exiting
            }
        });
        this.background.start();
    }

    @Override
    public void instruct(final T instruction) throws InterruptedException {
        this.instructions.put(instruction);
    }

    @Override
    public void close() throws Exception {
        this.background.interrupt();
    }
}
