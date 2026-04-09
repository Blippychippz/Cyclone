package com.micewine.emu.adapters;

import static com.micewine.emu.activities.MainActivity.deviceArch;
import static com.micewine.emu.adapters.AdapterRatPackage.CORE;
import static com.micewine.emu.adapters.AdapterRatPackage.DXVK;
import static com.micewine.emu.adapters.AdapterRatPackage.VKD3D;
import static com.micewine.emu.adapters.AdapterRatPackage.VK_DRIVER;
import static com.micewine.emu.adapters.AdapterRatPackage.BOX64;
import static com.micewine.emu.adapters.AdapterRatPackage.WINE;
import static com.micewine.emu.adapters.AdapterRatPackage.WINED3D;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.micewine.emu.fragments.RatManagerFragment;

public class AdapterTabPagerRatManager extends FragmentStateAdapter {
    public AdapterTabPagerRatManager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (deviceArch.equals("x86_64")) {
            return switch (position) {
                case 0 -> new RatManagerFragment(CORE);
                case 1 -> new RatManagerFragment(VK_DRIVER);
                case 2 -> new RatManagerFragment(WINE);
                case 3 -> new RatManagerFragment(DXVK);
                case 4 -> new RatManagerFragment(WINED3D);
                case 5 -> new RatManagerFragment(VKD3D);
                default -> throw new IllegalArgumentException("Invalid Fragment for Position " + position);
            };
        } else {
            return switch (position) {
                case 0 -> new RatManagerFragment(CORE);
                case 1 -> new RatManagerFragment(VK_DRIVER, "AdrenoToolsDriver");
                case 2 -> new RatManagerFragment(BOX64);
                case 3 -> new RatManagerFragment(WINE);
                case 4 -> new RatManagerFragment(DXVK);
                case 5 -> new RatManagerFragment(WINED3D);
                case 6 -> new RatManagerFragment(VKD3D);
                default -> throw new IllegalArgumentException("Invalid Fragment for Position " + position);
            };
        }
    }

    @Override
    public int getItemCount() {
        return deviceArch.equals("x86_64") ? 6 : 7;
    }

    public String getItemName(int position) {
        if (deviceArch.equals("x86_64")) {
            return switch (position) {
                case 0 -> "Core";
                case 1 -> "Drivers";
                case 2 -> "Wine";
                case 3 -> "DXVK";
                case 4 -> "WineD3D";
                case 5 -> "VKD3D";
                default -> throw new IllegalArgumentException("Unexpected value: " + position);
            };
        } else {
            return switch (position) {
                case 0 -> "Core";
                case 1 -> "Drivers";
                case 2 -> "Box64";
                case 3 -> "Wine";
                case 4 -> "DXVK";
                case 5 -> "WineD3D";
                case 6 -> "VKD3D";
                default -> throw new IllegalArgumentException("Unexpected value: " + position);
            };
        }
    }
}