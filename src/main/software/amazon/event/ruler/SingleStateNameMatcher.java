package software.amazon.event.ruler;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * Implements the { [ "exists" : false] } pattern by performing a binary search
 * of the key in the event keys.
 */
public class SingleStateNameMatcher implements NameMatcher<NameState> {

    /**
     * We only have one pattern for name matcher today. Hence, we can just keep
     * a single state here for simplicity. In the future, when multiple patterns
     * come here, we need a byte match.
     */
    private NameState nameState;

    @Override
    public boolean isEmpty() {
        return nameState == null;
    }

    @Override
    public NameState addPattern(@Nonnull final Patterns pattern,
            @Nonnull final Supplier<? extends NameState> resultSupplier) {
        if (nameState == null) {
            nameState = resultSupplier.get();
        }
        return nameState;
    }

    @Override
    public void deletePattern(@Nonnull final Patterns pattern) {
        nameState = null;
    }

    @Override
    public NameState findPattern(@Nonnull final Patterns pattern) {
        return nameState;
    }

    @Override
    public NameState getNextState() {
        return nameState;
    }
}