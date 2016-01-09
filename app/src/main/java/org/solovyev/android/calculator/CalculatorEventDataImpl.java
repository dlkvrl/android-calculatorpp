/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.calculator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class CalculatorEventDataImpl implements CalculatorEventData {

    private static final long NO_SEQUENCE = -1L;

    private final long eventId;
    private final Object source;
    private final long sequenceId;

    private CalculatorEventDataImpl(long id, long sequenceId, @Nullable Object source) {
        this.eventId = id;
        this.sequenceId = sequenceId;
        this.source = source;
    }

    @Nonnull
    static CalculatorEventData newInstance(long id, long sequenceId) {
        return new CalculatorEventDataImpl(id, sequenceId, null);
    }

    @Nonnull
    static CalculatorEventData newInstance(long id, long sequenceId, @Nonnull Object source) {
        return new CalculatorEventDataImpl(id, sequenceId, source);
    }

    @Override
    public long getEventId() {
        return this.eventId;
    }

    @Nonnull
    @Override
    public Long getSequenceId() {
        return this.sequenceId;
    }

    @Override
    public Object getSource() {
        return this.source;
    }

    @Override
    public boolean isAfter(@Nonnull CalculatorEventData that) {
        return this.eventId > that.getEventId();
    }

    @Override
    public boolean isSameSequence(@Nonnull CalculatorEventData that) {
        if (this.sequenceId == NO_SEQUENCE) {
            return false;
        }
        if (this.sequenceId == that.getSequenceId()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAfterSequence(@Nonnull CalculatorEventData that) {
        if (this.sequenceId == NO_SEQUENCE) {
            return false;
        }
        if (this.sequenceId > that.getSequenceId()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculatorEventDataImpl)) return false;

        CalculatorEventDataImpl that = (CalculatorEventDataImpl) o;

        if (eventId != that.eventId) return false;
        if (sequenceId != that.sequenceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (int) (sequenceId ^ (sequenceId >>> 32));
        return result;
    }
}