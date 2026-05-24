package io.github.mooy1.infinityexpansion;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockbukkit.mockbukkit.MockBukkit;

@Disabled("MockBukkit v1.21 incompatible with Paper 26.1.2 API - needs mockbukkit update")
class TestInfinityExpansion {

    @Test
    void testLoad() {
        MockBukkit.mock();
        MockBukkit.load(InfinityExpansion.class);
        MockBukkit.unmock();
    }

}
