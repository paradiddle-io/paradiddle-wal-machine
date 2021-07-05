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
package io.paradiddle.walmachine.log;

import io.paradiddle.walmachine.Instruction;
import io.paradiddle.walmachine.WriteAheadLog;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public final class InMemoryWriteAheadLog<T extends Instruction> implements WriteAheadLog<T> {
    private final List<T> instructions;

    public InMemoryWriteAheadLog() {
        this(new LinkedList<>());
    }

    public InMemoryWriteAheadLog(final List<T> instructions) {
        this.instructions = instructions;
    }

    @Override
    public void append(final T instruction) {
        this.instructions.add(instruction);
    }

    @Override
    public void forEach(final Consumer<T> consumer) {
        this.instructions.forEach(consumer);
    }
}
