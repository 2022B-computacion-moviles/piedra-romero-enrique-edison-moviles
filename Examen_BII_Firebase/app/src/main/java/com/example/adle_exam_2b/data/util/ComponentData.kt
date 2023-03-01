package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.ComponentEntity

class ComponentData {

    companion object {
        var componentEntityData = ArrayList<ComponentEntity>()

        init {
            componentEntityData.add(
                ComponentEntity(
                    1, "Resistor", "10K Ohm", false, 1
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    2, "Transistor", "NPN BD244B", false, 1
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    3, "Transistor", "PNP W4C40B", true, 1
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    4, "Transistor", "NPN BD244B", true, 2
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    5, "Resistor", "100K Ohm", false, 2
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    6, "Resistor", "250K Ohm", false, 3
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    7, "Transistor", "PNP 54CA1", true, 4
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    8, "Resistor", "10K Ohm", false, 4
                )
            )
        }
    }

}