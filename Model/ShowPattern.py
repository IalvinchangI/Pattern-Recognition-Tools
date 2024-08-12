from ReadPattern import PatternReader
import matplotlib.pyplot as plt
import numpy


DIR_PATH = "Model\\patterns"

for i, pattern_obj in enumerate(PatternReader.read_directory(DIR_PATH), 1):
    pattern = numpy.array(pattern_obj.pattern)
    velocity = numpy.linalg.norm(numpy.array(pattern_obj.velocity), axis=2)
    print(velocity)
    label = pattern_obj.label

    subplot = plt.subplot(2, 5, i)
    # subplot.imshow(pattern, cmap='gray', vmin=0, vmax=255)
    subplot.imshow(velocity, cmap='gray', vmin=0, vmax=3)
    subplot.set_title(label, fontsize=10)

    subplot.set_xticks([])
    subplot.set_yticks([])

plt.show()
