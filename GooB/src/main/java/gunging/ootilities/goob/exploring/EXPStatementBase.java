package gunging.ootilities.goob.exploring;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.minecraft.*;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMNetworkIndexed;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * The base implementation for an Item Explorer Statement
 *
 * @param <Elaboratee> The class that this expects for statements
 *                     to be resolved and elaborated.
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class EXPStatementBase<Elaboratee> implements ItemExplorerStatement<Elaboratee>, GBMNetworkIndexed, GOOBFuel, GOOBMinecraftFuel  {

    /**
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public EXPStatementBase(@NotNull GOOBWrapperBase<? extends Elaboratee, ?> wrapper, @NotNull GBMNamespacedKey<?> statementName) { this(wrapper.getMinecraft(), wrapper, statementName); }

    /**
     * @param statementName The internal name of this explorer statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public EXPStatementBase(@NotNull GOOBMinecraft minecraft, @NotNull GOOBWrapper<? extends Elaboratee, ?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        this.statementName = statementName;
        this.minecraft = minecraft;
        this.elaboratorWrapper = wrapper;
    }

    /**
     * The GOOB Wrapper used for the class
     *
     * @since 1.0.0
     */
    @NotNull GOOBWrapper<? extends Elaboratee, ?> elaboratorWrapper;

    /**
     * The engine this is running on
     *
     * @since 1.0.0
     */
    @NotNull GOOBMinecraft minecraft;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public @NotNull GOOBWrapper<? extends Elaboratee, ?> getElaboratorWrapper() { return elaboratorWrapper; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBMinecraft getMinecraft() { return minecraft; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }

    /**
     * @see #setNetworkIndex(int)
     *
     * @since 1.0.0
     */
    int networkIndex;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public void setNetworkIndex(int n) { networkIndex = n; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public int getNetworkIndex() { return networkIndex; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public String toString() { return getStatementName() + getOptions(); }

    /**
     * The internal name of this explorer statement
     *
     * @since 1.0.0
     */
    @NotNull final GBMNamespacedKey<?> statementName;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override  public @NotNull GBMNamespacedKey<?> getStatementName() { return statementName; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public int hashCode() { return networkIndex; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public boolean equals(Object obj) {

        // Comparison among statements
        if (!(obj instanceof ItemExplorerStatement<?>)) { return false; }
        ItemExplorerStatement<?> comparate = (ItemExplorerStatement<?>) obj;

        // Priority to network index comparison over namespace equals
        if (comparate instanceof GBMNetworkIndexed) {

            // Must match both name and options in its most fundamental form
            return (((GBMNetworkIndexed) comparate).getNetworkIndex() == getNetworkIndex()) && comparate.getOptions().equals(getOptions());
        } else {

            // Must match both name and options in its most fundamental form
            return (comparate.getStatementName().equals(getStatementName())) && comparate.getOptions().equals(getOptions());
        }
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<?> getElaboratorTarget() { return getElaboratorWrapper().getWrappedClass(); }
}
