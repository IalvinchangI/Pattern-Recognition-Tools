import argparse
import math
from pathlib import Path
import matplotlib.pyplot as plt
import numpy

from ReadPattern import PatternReader


def main():
    parser = argparse.ArgumentParser(
        description="[Pattern & Velocity Visualization]\n"
                    "Reads pattern data from a specified directory and plots the Pattern\n"
                    "and Velocity in separate windows. Supports viewing multiple data entries at once.",
        formatter_class=argparse.RawTextHelpFormatter,
        epilog="Usage Examples:\n"
               "  python ShowPattern.py                     # Plot both Pattern and Velocity using the default path\n"
               "  python ShowPattern.py path/to/data        # Plot both using a custom path\n"
               "  python ShowPattern.py -p path/to/data     # Plot only Pattern using a custom path\n"
               "  python ShowPattern.py -v                  # Plot only Velocity using the default path\n"
               "  python ShowPattern.py -pv path/to/data    # Plot both explicitly using a custom path\n"
               "  python ShowPattern.py -n 32               # Plot top 32 items using the default path"
    )
    
    parser.add_argument(
        "-p", "--pattern", 
        dest="plot_pattern", 
        action="store_true", 
        help="Open the Pattern plotting window.\n"
             "Default behavior: If neither -p nor -v is specified, both will be plotted."
    )
    
    parser.add_argument(
        "-v", "--velocity", 
        dest="plot_velocity", 
        action="store_true", 
        help="Open the Velocity plotting window.\n"
             "Default behavior: If neither -p nor -v is specified, both will be plotted."
    )
    
    parser.add_argument(
        "-n", "--num", 
        type=int, 
        default=16, 
        help="Specify the number of top entries to display.\n"
             "Default number: 16"
    )
    
    parser.add_argument(
        "path", 
        type=Path, 
        nargs="?", 
        default=Path("./patterns"), 
        help="The directory path containing the pattern data (supports absolute and relative paths).\n"
             "Default path: './patterns'"
    )
    
    args = parser.parse_args()

    if not args.plot_pattern and not args.plot_velocity:
        args.plot_pattern = True
        args.plot_velocity = True

    if not args.path.exists() or not args.path.is_dir():
        print(f"Error: Can not find '{args.path}'.")
        return

    # calculate rows and columns
    patterns = list(PatternReader.read_directory(str(args.path)))[:args.num]
    num_patterns = len(patterns)

    if num_patterns == 0:
        print("Error: No data found in the specified directory.")
        return

    cols = min(8, num_patterns)
    rows = math.ceil(num_patterns / cols)

    if args.plot_pattern:
        plt.figure("Pattern")
    if args.plot_velocity:
        plt.figure("Velocity")

    for i, pattern_obj in enumerate(patterns, 1):
        label = pattern_obj.label

        # plot Pattern
        if args.plot_pattern:
            pattern = numpy.array(pattern_obj.pattern)
            plt.figure("Pattern")
            ax_pat = plt.subplot(rows, cols, i)
            ax_pat.imshow(pattern, cmap="gray", vmin=0, vmax=255)
            ax_pat.set_title(label, fontsize=10)
            ax_pat.set_xticks([])
            ax_pat.set_yticks([])

        # plot Velocity
        if args.plot_velocity:
            velocity = numpy.linalg.norm(numpy.array(pattern_obj.velocity), axis=2)
            plt.figure("Velocity")
            ax_vel = plt.subplot(rows, cols, i)
            ax_vel.imshow(velocity, cmap="gray", vmin=-3, vmax=3)
            ax_vel.set_title(label, fontsize=10)
            ax_vel.set_xticks([])
            ax_vel.set_yticks([])

    plt.show()

if __name__ == "__main__":
    main()
